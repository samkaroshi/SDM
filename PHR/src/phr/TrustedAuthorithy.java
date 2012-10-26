
package phr;

import cpabe.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
/**
 *
 * @author luca
 */
public class TrustedAuthorithy {
    
    Cpabe cpabe;
    
    
    public TrustedAuthorithy(){
        cpabe = new Cpabe();
    }
    
    //info is the bsn in case of a client or the company name in case of an insurance company
    //in case of any other entity the info is useless
    public LinkedList<String>[] setup(String pubk_location, String mk_location, String type, String info) throws Exception {
        LinkedList<String> list[] = (LinkedList<String>[]) new LinkedList[2];
        try{
            cpabe.setup(pubk_location, mk_location);
            if((type.equalsIgnoreCase("patient")) || (type.equalsIgnoreCase("insurance"))) {
                //possibility to add a double check on the bsn and name of the insurance company
                //the whole attributes generation can be managed in a fancier way (e.g. transaction between
                //client and TA)
                list[0].add(type); //write attributes
                list[1].add(info); //read attributes
            }
            else {
                list[0].add(type); //write attributes
                list[1].add(type); //read attributes
            }
        }
        catch (IOException | ClassNotFoundException e) {}
        return list;
    }   
    
    public void keygen(String pubk_location, String prvk_location, String mk_location, String attr_str) throws IOException {
        try{
            cpabe.keygen(pubk_location, prvk_location, mk_location, attr_str);
        }
        catch (NoSuchAlgorithmException | IOException e) {}
    }
    
}