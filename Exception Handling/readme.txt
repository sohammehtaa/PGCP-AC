Exception Handling


Regarding Exception Handling in java.....
Any run time err occurs(eg file not found,accessing out of array size,accessing func from null ref, divide by 0)
---JRE(main thrd) --- creates matching exc class instance(java.io.FileNotFoundException,java.lang.ArrayOutOfBoundsExc,NullPointerExc,ArithmeticExc)
--- JRE checks -- if prog has proivided exc handling code ?
--- NO -- JRE aborts java code(by supplying def handler) & prints details --F.Q exc class name,reason behind failure & location details(err stack trace

--- YES (try---catch) JRE execs exc handling block & continues with the rest of the code.



syntax(key words) --- try,catch,finally,throw,throws
Inheritance hierarchy of exc classes
unchecked vs checked excs.
Creating custom excs
JDK 1.7 syntax --- try-with-resources(in I/O or device prog)



Checked & Unchked exception are detected or occur only in run-time.
JRE/JVM DOES NOT distinguish between them
Compiler(javac) differentiates between them
Javac forces handling of the checked exc. upon the prog.(Handling by supplying matching try-catch block or including it in the throws clause.)

Legal syntax
1. try {...} catch (ArithmeticException e){...}
2. try {...} catch (exc1 e){...} catch (exc2 e) {..} ....
3. try {...} catch (NPE e){} catch (AE e) {}catch(Exception e){catch-all}
3.5  try {...} catch (AE e){...} catch (NPE | AOB | ClassCastException e) {...}catch(Exception e){catch-all}
4. throws syntax ---
method declaration throws comma separated list of exc classes.
eg : Integer class API
public static int parseInt(String s) throws NumberFormatException
Thread class API
public static void sleep(long ms) throws InterruptedException

FileReader API
public FileReader(String fileName) throws FileNotFoundException

throws --- keyword meant for javac
Meaning -- Method MAY raise specified exc.
Current method is NOT handling it , BUT its caller should handle.
Mandatory--- only in case of un handled(no try-catch) chked excs(not extended from RuntimeException).
Use case --used in delegating the exception handling to the caller.

4.5 Throwable class API
 
1. public String toString() -- returns Name of exception class & reason.(detailed err mesg)
2. public String getMessage() -- rets error mesg of exception
3. public void printStackTrace() --- Displays name of exc class, error ,mesg, location details


5. finally --- keyword in exc handling : represents a block
finally -- block -- finally block ALWAYS survives(except System.exit(0/-1/-2) i.e terminating JVM)
i.e in the presence or absence of excs.
5.1 try{...} catch (Exception e){....} finally {....}  : will code continue in normal manner ? YES 
5.2 try{....NullPointerExc.....} catch (NullPointerException e){....} finally {....} --- will code continue in normal --
=> try block aborts --enter matching catch block --finally block executes

5.3 try{....NumberFormatExc.....} catch (NullPointerException e){....} finally {....} --- will code continue in normal --
=> -- NO --try block is aborted -- DOES NOT enter the catch --finally is executed
--JVM continues to search for MATCHING catch block 
if found -execs the catch block --continues 
if not found -- aborts the code.


5.3 try {...} finally {....}

try-with-resources block
From Java SE 7 onwards --- Java has introduced java.lang.AutoCloseable -- i/f
It represents --- resources that must be closed -- when no longer required.
Autocloesable i/f method
public void close() throws Exception--used for closing the resources.

Java I/O  classes(eg : BufferedReader,PrintWriter.....),Scanner -- have already implemented this i/f -- to automatically close resource when no longer required.

syntax of try-with-resources
try (//can open one or multiple AutoCloseable resources)
{ ......
} catch(Exception e)
{
}
eg : legal syntax
try(Scanner sc=new Scanner(System.in)) //try-with-resources 
{
 //use scanner
} // JVM will automatically call :  sc.close()

OR 
legal syntax

try(Scanner sc=new Scanner(System.in)) //try-with-resources 
{
 //use scanner
} // JVM will automatically call :  sc.close()
catch (Exception e) {...}


catch -all 

Creating Custom Exc(User defined exception or application exc)
Need :

1. Validations : In case of validation failures : Prog will have to throw custom exc class instance
2. B.L failures (eg : funds transfer : insufficient finds)  :  Prog will have to throw custom exc class  instance

1. Create a pkged public class which extends Throwable(not reco but legal)/Exception(recommended)/Error(not reco but legal)/RuntimeExc(not reco but legal)
eg : public class MyException extends Exception{
   public MyException(String mesg)
   {
    super(mesg);
   }
}
public class MyException2 extends RunTimeException{....}

2.CustExc(String msg) : overload the constr : to invoke the super-class constr.
of the form 
Exception (String msg)
OR  
CustExc(String msg,Throwable rootCause)
public Exception(String message,Throwable cause)


Objective :
Check the speed of vehicle on a freeway
Accept the speed using Scanner : can be speed too low(exc) or too high(exc) or in range


keyword -- throw --for throwing  exception.
JVM uses it to throw built-in exceptions(eg : NullPointerExc , IOException etc) & prog uses it throw custom exception(user defined excs) in case of B.L or validation failures.

syntax :
throw Throwable instance;
eg : 
throw new NullPointerExc();// no javac err
throw new InterruptedExc();// no javac err
throw new Throwable("abc");// no javac err
throw new Account(...);//javac err (provided it doesn't extend from Throwable hierarchy)
throw new AccountOverdrawnException("funds too low...");//proper usage
