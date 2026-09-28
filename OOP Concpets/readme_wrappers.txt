Regarding wrapper classes
1. What's need of wrapper classes?
---1. to be able to add primitive types to growable collection(growable data structure eg -- LinkedList)
--- 2.  wrapper classes contain useful api(eg --- parseInt,parseFloat....,isDigit,isWhiteSpace...)
2. What are wrappers? --- Class  equivalent for primitive types (i.e holding primitive value)

-- Inheritance hierarchy
java.lang.Object --- Character (char)
java.lang.Object --- Boolean
Object -- Number -- Byte,Short,Integer,Long,Float,Double
3. Constrs & methods --- for boxing & unboxing
boxing= conversion from prim type to the wrapper type(class type)
un-boxing = conversion from wrapper type to the prim type
eg 
Integer(int data) --- boxing 
Integer i1=new Integer(100);
//un-boxing
int data=i1.intValue();


Integer i1=100;//no err from JDK 1.5
sop(i1);
int data=1234;
i1++;//Integer--->int(auto unboxing), inc ,auto box
Object o=123.45;//auto-boxing(double--->Double)--up casted to Object
Number n1=true;//auto-box----X(up casted) to Number
Object o2=false;//auto box -- up casting
Double d1=1234;//auto boxing (int --->Integer) ---X--Double


4. JDK 1.5 onwards --- boxing &unboxing performed automatically by java compiler,when required. --- auto-boxing , auto-unboxing,
5. examples








