import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;
	
	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;
	
	public ResourceCentreTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList= new ArrayList<Camcorder>();
		chromebookList= new ArrayList<Chromebook>();
	}

	
	@Test
	public void addCamcorderTest() {
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);		
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());
		
		//The item just added is as same as the first item of the list
		assertSame("Test that Camcorder is added same as 1st item of the list?", cc1, camcorderList.get(0));
		
		//Add another item. test The size of the list is 2?
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, camcorderList.size());
	}
	@Test
	public void addChromebookTest() {
		//fail("Not yet implemented");
		// write your code here

	}
	
	@Test
	public void retrieveAllCamcorderTest() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//test if the list of camcorders retrieved from the SourceCentre is empty
				String allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);
				String testOutput = "";
				assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
				
		//Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());
		
		//test if the expected output string same as the list of camcorders retrieved from the SourceCentre
		allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0012", "Sony DSC-RX100M7", "Yes", "", 20);
	
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
		
	}
	@Test
	public void retrieveAllChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
		
	}

	@Test
	public void doLoanCamcorderTest() {

	}
	
	@Test
	public static boolean doLoanChromebookTest(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
		// fail("Not yet implemented");
		// write your code here
		boolean isLoaned = false;
		for (int i = 0; i < chromebookList.size(); i++) {
			if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag())
					&& chromebookList.get(i).getIsAvailable() == true) {

				chromebookList.get(i).setIsAvailable(false);
				chromebookList.get(i).setDueDate(dueDate);

				isLoaned = true;
			}
		}
		return true;
	}
	  
	public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
   
		// write your code here
     
		ResourceCentre.viewAllChromebook(chromebookList);
		String tag = Helper.readString("Enter asset tag > ");
		String due = Helper.readString("Enter due date > ");

		Boolean isLoaned = doLoanChromebookTest(chromebookList, tag, due);

		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
         
		} else {
			System.out.println("Chromebook " + tag + " loaned out");
		}
	}
	
	@Test
	public void doReturnCamcorderTest() {
        ResourceCentre.doReturnCamcorder(camcorderList, cc1.getAssetTag());
		assertNotNull("Test if there is valid Camcorder arraylist to return to", camcorderList);

        assertTrue("Test if item has been returned", cc1.getIsAvailable());
        assertTrue("Test if item has been returned", cc2.getIsAvailable());
		
	}
	@Test
	public static boolean doReturnChromebookTest(ArrayList<Chromebook> chromebookList, String tag) {
		//fail("Not yet implemented");
		// write your code here
		    for (int i = 0; i < chromebookList.size(); i++) {
			    if (tag.equalsIgnoreCase(chromebookList.get(i).getAssetTag()) && chromebookList.get(i).getIsAvailable() == false) 
				    chromebookList.get(i).setIsAvailable(true);
			            chromebookList.get(i).setDueDate("");
			    
			    isReturned = true;
		    }
		return isReturned
	}
	
	  public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
   	 
		  // write your code here
   	 
		  ResourceCentre.viewAllChromebook(chromebookList);
		  String tag = Helper.readString("Enter asset tag > ");
		  Boolean isReturned = doReturnChromebook(chromebookList, tag);
		  // write your code here
		  if (isReturned == false) {
			  System.out.println("Invalid asset tag");
		  } else {  
			  System.out.println("Chromebook " + tag + " returned");
		  }
	  }
	
	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
