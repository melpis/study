import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {

	private Map<String , List<Map<String, String>>> db = null;
	private Map<String,Map<String, Integer>> index = new HashMap<String, Map<String,Integer>>();
	
	
	public DB()
	{
		db= new  HashMap<String , List<Map<String, String>>>(); 
	}
	
	public void createTable(String tableName){
		List<Map<String,String>> tableData= new ArrayList<Map<String,String>>();
		db.put(tableName, tableData);
		
		Map<String, Integer> tableIndex = new HashMap<String, Integer>();
		index.put(tableName,tableIndex);
		
	}
	
	//insert
	public void add(String tableName, Map<String,String> data)
	{
		this.db.get(tableName).add(data);
		
		//index add
		this.index.get(tableName).put(data.get("SEQ"), (Integer)this.db.get(tableName).size()-1);
		
		
		
		
		
		
	}
	
	//delete
	public void remove(String tableName,String pk,String value)
	{
		List<Map<String, String>> boardList = this.db.get(tableName);
		int indexNum= this.index.get(tableName).get(value);
		boardList.remove(indexNum);
		
		
//		for(int indexI = 0; indexI < boardList.size(); indexI++){
//			Map<String, String> temp = boardList.get(indexI);
//			if(temp.get(pk).equals(value))
//			{
//				boardList.remove(indexI);
//				break;
//			}
//			
//		}
		
		
	}
	
	//select
	public List<Map<String,String>> get(String tableName)
	{
		return get(tableName,null,null);
	}
	
	public List<Map<String,String>> get(String tableName,String pk,String value)
	{
	
		List<Map<String,String>> returnList= new ArrayList<Map<String,String>>();
		
		if(pk == null)
		{
			
			returnList=this.db.get(tableName);
			
		}else{
			
			int indexNum = this.index.get(tableName).get(value);
			returnList.add(this.db.get(tableName).get(indexNum));
			
		}
		
		
		return returnList;
	}
	
	
	
	//update
	public void set(String tableName, Map<String,String> data,String pk, String value) 
	{
		
		List<Map<String, String>> boardList = this.db.get(tableName);
		int indexNum= this.index.get(tableName).get(value);
		boardList.set(indexNum, data);
		
	}
	
	
}
