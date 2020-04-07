package venn;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ControlTest.class, DataTest.class, EntryCustomizeTest.class, SaveLoadTest.class,
	                  MainTest.class})
public class AllVennTest {
	
	public static int time = 1000;
	public static int stime = 1000;
	public static int wtime = 15000;

}
