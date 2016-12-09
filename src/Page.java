

/**
 * Created by derianescobar on 9/28/16.
 */


public class Page {
    int vpn;
    byte[] byt;

    /**param
     *
     * @param offset
     * @return
     */
    public byte getData(int offset) {
        if (offset < 0 || offset > 99){
            throw new IllegalArgumentException("invalid offset");//Handles cases where offset is out of bounds
        }
        else {
            return byt[offset];
        }
    }

    public Page(int vpn, byte[] byt) {
        this.vpn = vpn; //Constructor page used to make pages using vpn and bytes
        this.byt = byt;


    }
}

