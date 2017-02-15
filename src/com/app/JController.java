package com.app;

import java.util.List;
import java.util.Map;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.taskqueue.TaskQueuePb.TaskQueueAddRequest.RequestMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class JController {
	@RequestMapping("/notesapp")
	public void display()
	{
	   System.out.println("Hello via controller");
	}
	
	@RequestMapping("/add")
	public void addNotes(@RequestParam Map<String,String> data)
	{
		String name=data.get("uname");
		String content=data.get("notes");
	System.out.println(name+  "   "+content);
		Notes notes=new Notes();
		notes.setUserName(name);
		notes.setNoteContent(content);
		PersistenceManager pm =  JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();

		try {
			pm.makePersistent(notes);
		} finally {
			pm.close();
		}
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/view",produces = "application/json" )
	public String viewNotes(ModelMap result)
	{
		PersistenceManager pm =  JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		javax.jdo.Query q= pm.newQuery(Notes.class);
//		q.setFilter("userName == nameParameter");
//		q.declareParameters("String nameParameter");
//
//		List<Notes> nl=null;
//		try
//        {
//			nl=(List<Notes>) q.execute("hello");
//        	System.out.println(nl.get(0).getNoteContent());
//        	
//        }
//		finally
//		{
//			q.closeAll();
//			pm.close();
//		}
		List <Notes> results=null;
		int i=0;
		String res="";
        Gson a = new Gson();
		try {
			results = (List<Notes>) q.execute();
			while(i<results.size()) {
				// good for listing
			//	Gson gson = new GsonBuilder().create();
		      //  gson.toJson("Hello", System.out);
		       // gson.toJson(123, System.out);
               // System.out.println(results.get(i).dis() + " "+i);
    			
				 res+=a.toJson(results.get(i));
				//System.out.println(results.get(i).dis() + " "+i);
				i++;
			}
			System.out.println(res);
			result.addAttribute(res);
			
		} finally {
			q.closeAll();
			pm.close();
		}
		return res; 
	}
}
