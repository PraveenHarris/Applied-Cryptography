package T1;

import util.MyTools;

public class T11 {
    public static void main(String[] args) {
        int key1 = 49;
        String key2 = "MIRACULOUS";
        String ct = "DWDAJIECVBQKKHCMLKUNQTVNINSHBSFLVPGHOGNWYXVRCNFFYJMLZAVCZBZ";

        String pt0 = "";
        for (int i=0; i<ct.length(); i++) {
            char shiftLetter = key2.charAt(i%key2.length());
            byte shifted = MyTools.shiftBy(ct.charAt(i), shiftLetter-65, false);
            pt0 += (char) (int) shifted;
        }


//        for (int i=0; i<pt0.length(); i++) {
//            System.out.print(pt0.charAt(Math.floorMod(i-49, ct.length())));
        System.out.println(pt0.substring(49));
//        }

    }
}
