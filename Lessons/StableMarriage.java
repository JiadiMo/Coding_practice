import java.util.*;

public class StableMarriage {
	
	public static void matchMaker(Map man,Map woman){
		Map<String, String[]> couple = new TreeMap<String, String[]>();
		Set men=man.keySet();
		Set women=woman.keySet();
		System.out.println(men);
		Set menfree=men;
		
		while (menfree!=null){
			//men=menfree.pop()
		}
		for(int i = 0;i <3;i++){
			
		}
		
	}
	public static void main(String args[]){
		Map<String, String[]> man = new TreeMap<String, String[]>();
		man.put("m1", new String[]{"w1", "w2", "w3"});
		man.put("m2", new String[]{"w1", "w3", "w2"});
		man.put("m3", new String[]{"w1", "w3", "w2"});
		Map<String, String[]> woman = new TreeMap<String, String[]>();
		woman.put("w1", new String[]{"m2", "m1", "m3"});
		woman.put("w2", new String[]{"m1", "m2", "m3"});
		woman.put("w3", new String[]{"m3", "m1", "m2"});
		
		matchMaker(man,woman);
		
		int NUM=4;
		int[][] manPick={{2,3,1,0},{2,1,3,0},{0,2,3,1},{1,3,2,0}};    
	    int[][] womanPick = {{0,3,2,1},{0,1,2,3},{0,2,3,1},{1,0,3,2}};  
		//String[]man={"m1","m2","m3","m4"};
		//String[]woman={"w1","w2","w3","m4"};
		String[]manToWoman=new String[4];
		
		System.out.println(manToWoman[0]);
	}
}
