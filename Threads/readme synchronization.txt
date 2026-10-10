Race condition

The situation where two or more threads compete for the same resource, where the sequence in which the resource is accessed is significant, is called race conditions.

Critical Section
A code section that leads to race conditions is called a critical section.
eg : Joint Bank Account : shared resource
critical sections : updateBalance n checkBalance

Preventing Race Conditions
To prevent race conditions from occurring you must make sure that the critical section is executed as an atomic instruction. That means that once a single thread is executing it, no other threads can execute it until the first thread has left the critical section.

Synchronization = Applying thread safety or applying locks

When is synchronization required ?
It's required iff multiple threads are sharing SAME common resource(eg : any collection,data file , db table ,socket , reservation...) & one thread is accessing & other one is modifying the resource.

How to apply synchronization in java ?
keyword -- synchronized.
Can appear as method modifier or at block level.

To avoid race condition / guard critical section , you apply synchronization.

Using synchronized keyword , a lock is applied at the object level.(i.e instance of the shared resource
eg : JointAccount)

Important statements

1. lock/monitor can be associated with any java object.
2. When does thrd need to acquire the lock (=enter the monitor)?-- if its invoking either synchronized methods or code from synchronized blocks
3. Can single thrd acquire multiple locks -- YES
BUT a single lock CAN NOT be shared across multiple threads.

4. Blocking trigger 
unable to acquire lock(enter monitor) : Blocked on monitor/lock
Un blocked -- lock released / monitor free.(synchronized method rets or synchronized block over)
5. If a thread invokes  sleep(or invokes join,yield,notify) or encounters context switching , it holds any locks it has , it doesn't release them.

What's the need of synchronized blocks?

1. Instead of writing long synchronized methods (n thus reducing the performance due to larger extent of the lock) , identify critical section & guard it using synchronized block.

2. While using inherently thread un safe API(StringBuilder, ArrayList,LinkedList,HS,LHS,HM...) in multi thrded environment : you can still apply thread safety : using synchronized blocks.

synchronized block syntax

synchronized(shared resource ref.)
{ 
Access the methods of shared resource in mutually exclusive manner.
}

1. Only methods (or blocks) can be synchronized, not variables or classes.
2. Each object has just one lock.
3. Not all methods in a class need to be synchronized. A class can have both
synchronized and non-synchronized methods.
4.  If two threads are about to execute a synchronized method in a class, and both threads are using the same instance of the class to invoke the method,only one thread at a time will be able to execute the method. The other thread will need to wait until the first one finishes its method call. In otherwords, once a thread acquires the lock on an object, no other thread can enter ANY of the synchronized methods in that class (for that object).


5. If a class has both synchronized and non-synchronized methods, multiple
threads can still access the class's non-synchronized methods. If you have methods that don't access the data you're trying to protect, then you don't
need to synchronize them. Synchronization can cause a hit in some cases (or
even deadlock if used incorrectly), so you should be careful not to overuse it.
6. If a thread goes to sleep(or invokes join,yield,notify) or encounters context switching , it holds any locks it has it doesn't release them.
7.  A thread can acquire more than one lock. For example, a thread can enter a
synchronized method, thus acquiring a lock, and then immediately invoke
a synchronized method on a different object, thus acquiring that lock as
well. As the stack unwinds, locks are released again. Also, if a thread acquires
a lock and then attempts to call a synchronized method on that same
object, no problem. The JVM knows that this thread already has the lock for
this object, so the thread is free to call other synchronized methods on the
same object, using the lock the thread already has.
eg : 
class A {
private B b1;
 synchrnized void test()
 {
   ...
   b1.testMe();
 }
}
class B 
{

 synchronized void testMe()
 {
  //some B.L
 }
}
Similar can be achieved using nested synchronized blocks.

8. You can synchronize a block of code rather than a method.
When to use synched blocks?
Because synchronization does hurt concurrency, you don't want to synchronize
any more code than is necessary to protect your data. So if the scope of a method is
more than needed, you can reduce the scope of the synchronized part to something
less than a full method to just a block. OR when u are using Thread un-safe(un-sunchronized eg -- StringBuilder or HashMap or HashSet) classes in your appln.
-----------------------------
Regarding static & non -static synchronized
1. Threads calling non-static synchronized methods in the same class will
only block each other if they're invoked using the same instance. That's
because they each lock on "this" instance, and if they're called using two different
instances, they get two locks, which do not interfere with each other.
2. Threads calling static synchronized methods in the same class will always
block each other they all lock on the same Class instance.

3.  A static synchronized method and a non-static synchronized method
will not block each other, ever. The static method locks on a Class
instance(java.lang.Class<?>)  while the non-static method locks on the "this" instance these actions do not interfere with each other at all.







