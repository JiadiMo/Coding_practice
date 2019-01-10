import java.util.LinkedList;

public class StableMatching {
    public static int[][] manPrefer = {{1,2,3},{1,3,2},{1,3,2}};//man pick list
    public static int[][] womanPrefer = {{2,1,3},{1,2,3},{3,1,2}}; //woman pick list
    public static LinkedList<LinkedList<Integer>> manPreferList = new LinkedList<LinkedList<Integer>> ();
    public static LinkedList<Integer> freeMan = new LinkedList<Integer> ();
    public static int[] chosedWoman = new int[3];//Judge women is chosed,1:True,0:False
    public static int[] result = new int[3];//

    public static void main(String[] args) {
        //Initialize
    	for (int i = 0 ; i < 3 ; i++) {
            LinkedList<Integer> temp = new LinkedList<Integer> ();
            for (int j = 0 ; j < 3 ; j++){
                temp.offer ( manPrefer[i][j] );
            }
            manPreferList.offer ( temp );
        }
        for (int i =0 ; i < 3 ; i++) {
            freeMan.offer ( i );
            chosedWoman[i] = 0;
            result[i]=-1;
        }
        //Start
        while (!freeMan.isEmpty ()) {
            int chosingMan = (int)freeMan.poll();//get man
            int chosingMansWoman = (int)((LinkedList<?>)manPreferList.get ( chosingMan )).poll ()-1;//get index of woman chosed by man
            if (chosedWoman[chosingMansWoman] == 1) { //this woman is not single
                //find current bf
                int nowboyfriend = 0;
                while (result[nowboyfriend] != chosingMansWoman) {
                    nowboyfriend++;
                }

                int nowbfPos=0;
                int chosingManPos=0;
                for(int i=0;i<3;i++){
                	if(womanPrefer[chosingMansWoman][i]-1 == nowboyfriend)
                		nowbfPos=i;
                	if(womanPrefer[chosingMansWoman][i]-1 == chosingMan)
                		chosingManPos=i;
                }
                if(chosingManPos>=nowbfPos){
                	freeMan.offer ( chosingMan);
                }else { 
                    freeMan.offer ( nowboyfriend );
                    result[nowboyfriend] = -1;
                    result[chosingMan] = chosingMansWoman;
                }

            } else { //this woman is single
                chosedWoman[chosingMansWoman] = 1;
                result[chosingMan] = chosingMansWoman;
            }
        }
        for (int j = 0 ; j < 3 ; j ++) {
            System.out.println ("m"+(j+1)+" : w"+(result[j]+1));
        }
    }
}
