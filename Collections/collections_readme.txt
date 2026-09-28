List<E> features
1. List represents ordered collection --- order is significant(It remembers the order of insertion)
2. Allows null references
3. Allows duplicates
4. Supports index based operation



java.util.ArrayList<E> -- E -- type of ref.
1. ArrayList<E>  -- constructor
API 
ArrayList() -- default constructor. -- creates EMPTY array list object , with init capacity=10,size=0;
eg ---ArrayList<Integer> l1=new ArrayList<>();

1.5 1. ArrayList<E>  -- constructor
API 
public ArrayList(int capacity) -- -- creates EMPTY array list object , with init capacity=capacity,size=0;
eg ---ArrayList<Integer> l1=new ArrayList<>(100);
l1.add(1);.....l1.add(100);
l1.add(101);//capa=150 --as per JVM spec.


2. add methods
boolean add(E e)  --- append 
void add(int index,E e) --- insert
void addAll(Collection<E> e) -- bulk append operation
eg : l1 --- AL<Emp>
l1.addAll(.....);
AL,LL,Vector --- legal
HS,TS,LHS --legal
HM,LHM,TM --illegal --javac error


2.5 Retrieve elem from list 
E get(int index)
index ranges from ---0 ---(size-1)
java.lang.IndexOutOfBoundsException

3. display list contents using --- toString


4. Attaching Iterator
Collection<E> interface method -- implemented by ArrayList
Iterator<E> iterator()
---places iterator BEFORE 1st element ref.
Iterator<E> i/f methods 
boolean hasNext() -- rets true if there exists next element, false otherwise.

E next() --- returns the element next to iterator position

void remove() -- removes last returned element from iterator.

Limitation --- type forward only & can start from 1st elem only.

Regarding exceptions with Iterator/List
1. java.util.NoSuchElementException -- thrown whenever trying to access the elem beyond the size of list via Iterator/ListIterator
2. java.lang.IllegalStateException --- thrown whenever trying to remove elem before calling next().
3. java.util.ConcurrentModificationException-- thrown typically --- when trying to use same iterator/list iterator --after structrually modifying list(eg add/remove methods of list)
Above describes fail-fast behaviour of the Iterator/ListIterator

Exception while accessing element by index.

4. java.lang.IndexOutOfBoundsException -- thrown typically  -- while trying to access elem beyond size(0---size-1) --via get


6. Attaching for-each  = attaching implicit iterator.

Attaching ListIterator ---scrollable iterator or to beign iteration from a specific element -- List ONLY or list specific iterator.
ListIterator<E> listItearator() --places LI before 1st element
ListIterator<E> listItearator(int index) --places LI before specified index.



4. search for a particular element in list
boolean contains(Object o)
NOTE : Based upon public boolean equals(Object o)

5. searching for 1st occurrence
use -- indexOf
int indexOf(Object o)
rets index of 1st occurrence of specified elem. Rets -1 if elem not found.
searching for last occurrence 
use -- lastIndexOf
int lastIndexOf(Object o)
rets index of last occurrence of specified elem. Rets -1 if elem not found.

5.5
E set(int index,E e)
Replaces old elem at spepcified index by new elem.
Returns old elem


6. remove methods
E remove(int index) ---removes elem at specified index & returns removed elem.

boolean  remove(Object o) --- removes element specified by argument , rets true -- if elem is removed or false if elem cant be removed.

7. conversion from collection to array
Object[] toArray() -- non generic version --- rets array of objects

T[] toArray(T[] type)
T = type of collection .
Rets array of actual type.


Objectives in Integer list
0. Create ArrayList of integers & populate it.
1. check if element exists in the list.
2. disp index of 1st occurance of the elem
3. double values in the list --if elem val > 20
4. remove elem at the specified index
5. remove by elem. -- rets true /false.


NOTE : 
For searching or removing based upon primary key , in List Implementation classes --- All search/remove methods (contains,indexOf,lastIndexOf,remove(Object o)) -- based upon equals method(of type of List eg --Account/Customer/Emp....)
For correct working
1. Identify prim key & create overloaded constr using PK.
eg : public Emp(int id) { this.id=id;}

2. Using PK , override equals for content equality.

Usage eg : ArrayList<Emp> emps=new AL<>();
emps.add(e1);//id=10
emps.add(e2);//id=20
emps.add(e3);//id=30

int index=emps.indexOf(20);//int ---> Integer --> Object (Integer)
Integer i=new Integer(20); // javac 
//internally invokes equals : whose equals --Object | Integer | Emp | NOA
invokes equlas on Integer  class
i.equals(e1)  ---since it's incomptabile types --rets false
i.equals(e2)  ---since it's incomptabile types --rets false
i.equals(e3)  ---since it's incomptabile types --rets false
Thus : indexOf rets -1
sop(index);// -1

Solution : 
Emp e=new Emp(20);
int index=emps.indexOf(e);
//internally invokes equals : whose equals --Object | Integer | Emp | NOA
invokes equals on Emp class
e.equals(e1)  ---it's comptabile types BUT ids are different --rets false
e.equals(e2)  --- it's comptabile types --ids are SAME --rets true

Thus : indexOf rets 1
sop(index);// 1



Sorting --- For sorting elements as per Natural(implicit i.e criteria defined within UDT class definition) ordering or Custom(explicit i.e criteria defined outside UDT , in a separate class or anonymus iner class)

Steps for Natural ordering
Natural Ordering is specified in generic i/f
java.lang.Comparable<T>
T -- UDT , class type of the object to be compared.
eg -- Emp,Account , Customer
I/f method
int compareTo(T o)
Steps 
1. UDT must implement Comparable<T>
eg : public class Account implements Comparable<Account>
2. Must override method
public int compareTo(T o)
{
 use sorting criteria to ret
< 0 if this < o,
=0 if this = o
> 0 if this > o

}
3.Use java.util.Collections class API
Method
public static void sort(List<T> l1)
l1 -- List of type T.

sort method internally invokes compareTo method(prog supplied) of UDT & using advanced sorting algorithm , sort the list elems.


Limitation of natural Ordering
Can supply only 1 criteria at given time & that too is embedded within UDT class definition
Instead keep sorting criteria external --using Custom ordering


Typically use -- Natural ordering in consistence with equals method.


Alternative is Custom Ordering(external ordering)
I/f used is --- java.util.Comparator<T>
T -- type of object to be compared.
 
Steps
1. Create a separate class (eg. AccountBalComparator) which implements Comparator<T>
eg 
public class AccountBalComparator implements Comparator<Account>

2.Implement(override) i/f method -- to supply comparison criteria.

int compare(T o1,T o2)
Must return 

< 0 if o1<o2
=0 if o1=o2
> 0 if o1 > o2
3. Invoke Collections class method for actual sorting.
public static void sort(List<T> l1,Comparator<T> c)
parameters
l1 --- List to be sorted(since List is i/f --- any of its implementation class inst. can be passed)
c -   instance of the class which has implemented compare method.(or implemented Comparator)

Internally sort method invokes compare method from the supplied Comparator class instance.

More on generic syntax
Constructor of ArrayList(Collection<? extends E> c)
? -- wild card in generic syntax (denotes any unknown type)
--Added for supporting inheritance in generics.'

extends -- keyword in generics, to specify upper bound
? extends E -- E or sub type
Complete meaning --- Can create new populated ArrayList of type E , from ANY Collection(ArrayList,LinkedList,Vector,HashSet,LinkedhashSet,TreeSet) of type E or its sub type.
ArrayList<Emp> l1=new ArrayList<>();
l1.add(new Emp(1,"aa",1000);
l1.add(new Emp(2,"ab",2000);
ArrayList<Emp> l2=new ArrayList<>(l1);
sop(l2.size());
----------
 HashSet<Emp> hs=new HashSet<>();
hs.add(new Emp(1,"aa",1000);
hs.add(new Emp(2,"ab",2000);
l2=new ArrayList<>(hs);
----
Vector<Mgr> v1=new Vector<>();
v1.add(new Mgr(....));
v1.add(new Mgr(....));
ArrayList<Emp> l2=new ArrayList<Mgr>(v1);
AL<Mgr> mgrs=new AL<>(hs);

Map API
HashMap<K,V> --
1. un-sorted(not sorted as per Natural ordering or custom ordering based criteria) & un-ordered(doesn't remember order of insertion) map implementation class.
2. No duplicate keys.
3. Guarantees constant time performance --- via 2 attributes --initial cpacity & load factor.
4. Allows null key reference(once).
5. Inherently thrd unsafe.

HashMap constrs
1. HashMap<K,V>() --- creates empty map , init capa = 16 & load factor .75
2. HashMap<K,V>(int capa) --- creates empty map , init capa specified & load factor .75
3.HashMap<K,V>(int capa,float loadFactor) --- creates empty map , init capa  & load factor specified

4. HashMap constrcutor for creating populated map
HashMap(Map <? extends K,? extends V> m)
? -- wild card in generics, represents unknown type
extends -- represents upper bound
? extends K --- K or its sub type
? extends V -- V or its sub type.
Complete meaning -- Creates populated HM<K,V> from ANY map(ie. any Map imple class)
of type  K or its sub type & V or its sub type.
eg : Suppose Emp <---- Mgr

HM<Integer,Emp> hm=new HM<>();
hm.put(1,e1);
hm.put(2,m1);
HM<Integer,Emp> hm2=new HM<>(hm);
sop(hm2);
LHM<Integer,Emp> lhm=new LHM<>(hm);//legal
HM<Integer,Mgr> hm3=new HM<Integer,Emp>(hm);//javac error

TM<Integer,Mgr> hm4=new TM<>();
hm4.put........
HM<Integer,Emp> hm5=new HM<>(hm4);


HM(Map<? extends K,? extends V>map)

Important Methods : 
put,get,size,isEmpty,containsKey,containValue,remove




If map sorting involves key based sorting criteria --- can be sorted by converting into TreeMap
Constructors of TreeMap
1. TreeMap() -- Creates empty map , based upon natural ordering of keys
2. TreeMap(Map<? extends K,? extends V> map)
Creates populated map , based upon natural ordering of keys
3. TreeMap(Comparator<? super K> c)
 Regarding generic syntax & its usage in TreeMap constructor.
<? super K>
? --- wild card --- any unknown type
super --- gives lower bound
K --- key type
? super K --- Any type which is either K or its super type.

TreeMap(Comparator<? super K> c)  --- creates new empty TreeMap, which will sort its element as per custom ordering(i.e will invoke compare(...) of Key type )


<? extends K>
? -- any type or wild card
extends -- specifies upper bound 
K -- key type
? extends K --- Any type as Key type or its sub type.

same meaning for <? extends V>
TreeMap(Map<? extends K,? extends V> m)

Limitations on Maps 
1. Maps can be sorted as per key's criteria alone.
2. can't attach iterators/for-each(till JDK 1.7)/for
3 Maps can be searched as per key's criteria alone.


To fix --- get a collection view of a map (i.e convert map to collection)

API of Map i/f

1. To get set of keys asso. with a Map
Set<K> keySet();
2. To get collection of values from a map
Collection<V> values();
3. To get set of Entries(key & val pair) ---
entrySet
Set<Map.Entry> entrySet()
Methods of Map.Entry
K getKey()
V getValue()
