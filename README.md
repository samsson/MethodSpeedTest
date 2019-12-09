# MethodSpeedTest

Java application to test the execution speed of methods and compare two for best performance CPU wise.

To run, execute main() of SpeedTest.java

To test methods, edit SpeedTest.java with custom functions at:

  startTime = System.nanoTime();
  
  /* Your function call here */
  
  run1result = MethodLibrary.equalsNofunction(commandLineArguments, "calc.exe");
  runtime1 = runtime1 +  System.nanoTime() - startTime;
  
  startTime = System.nanoTime();
  
  /* Your second function call here */
  
  run2result = MethodLibrary.equalsfunction(commandLine, commandLineArguments);
  runtime2 = runtime2 + System.nanoTime() - startTime;
