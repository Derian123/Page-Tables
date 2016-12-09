/**
 * Created by derianescobar on 9/28/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class OS {
    Page[] Pg;
    int[] tablePage;
    int numBytes;
    int num;

    /**param
     *
     * @param vpn
     * @return
     */
    public int getPPN(int vpn) {

        return tablePage[vpn];

    }

    /**param
     *
     * @param virtAddress
     * @return
     */
    public byte getDataAtVirtAddress(int virtAddress) {
        num = tablePage[0]; //Sets num to become first number in array
        int shift = (int) (Math.log(numBytes) / Math.log(2)); //Uses log of base 2 to get the amount of bits needed to shift
        int address = virtAddress & (int) (Math.pow(2, num) - 1);//raises 2 to the num and subtracts 1 in order to get address
        int offset = address & (numBytes - 1);//Use numbytes inorder to get the number needed to compare to get the offset
        int vpn = virtAddress >> shift;

        int ppn = getPPN(vpn);
        return (Pg[ppn].getData(offset));
    }

    /**param
     *
     * @param ppn
     * @return
     */
    public Page getPage(int ppn) {

        return Pg[ppn];
    }

    /**param
     *
     * @param filename
     */
    public OS(String filename) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(filename));
            String reader = read.readLine();//Reads file
            String[] lines = reader.split(" ");//splits on spaces only for the first line
            numBytes = Integer.parseInt(lines[1]); //parses the number of bits

            tablePage = new int[Integer.parseInt(lines[0])];
            Pg = new Page[Integer.parseInt(lines[0])];

            for (int i = 0; i < tablePage.length; i++) {
                reader = read.readLine();
                lines = reader.split("->");
                tablePage[i] = Integer.parseInt(lines[1]);
            }
            for (int i = 0; i < tablePage.length; i++) {
                reader = read.readLine();
                byte[] byt = new byte[tablePage[0]];
                byt = reader.getBytes("UTF-8");
                Pg[i] = new Page(i, byt);//uses the page constructor in order to make a new page
            }

        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public static void main(String[] args) throws InterruptedException {

        String file = "/Users/derianescobar/IdeaProjects/Project 2/src/proj2_data_large.txt";
        OS os = new OS(file);
        int[][] Table = new int[750][2];//Sets size of the 2D array
        Random random = new Random();//Makes a random number generator

//
    }
}


