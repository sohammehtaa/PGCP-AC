I/O handling

Desc of FileInputStream --- java.io.FileInputStream 
bin i/p stream connected to file device(bin/char) -- to read data.

Desc of FileOutputStream --- java.io.FileOutputStream 
bin o/p stream connected to file device(bin/char) -- to write data.

Desc of FileReader--- java.io.FileReader
char i/p stream connected to file device(char) -- to read data.

Desc of FileWriter--- java.io.FileWriter
char o/p stream connected to file device(char) -- to write data.

Objective --- Read data from text file in buffered manner.

1. java.io.FileReader(String fileName) throws FileNotFoundException
--- Stream class to represent unbuffered char data reading from a text file.
Has methods -- to read data using char/char[]
eg -- public int read() throws IOException

public int read(char[] data) throws IOException
Usage eg-- char[] data=new char[100];
int no= fin.read(data);

public int read(char[] data,int offset,int noOfChars) throws IOException
Usage eg-- char[] data=new char[100];
int no= fin.read(data,10,15);
eg -- 12 chars available 
no=12;data[10]----data[21]


1.5 FileReader(File f) throws FileNotFoundException
java.io.File -- class represents path to file or a folder.


2. Improved version -- Buffered data read .
For char oriented streams--- java.io.BufferedReader(Reader r)

API of BR --- 
String readLine() --- reads data from a buffer in line by line manner-- & rets null at end of Stream condition.




Objective -- Replace JDK 1.6 try-catch-finally BY JDK 1.7 try-with-resources syntax.
Meaning --- From Java SE 7 onwards --- Introduced java.lang.AutoCloseable -- i/f
It represents --- resources that must be closed -- when no longer required.
i/f method
public void close() throws Exception-- closing resources.
java.io --- classes -- have implemented this i/f -- to auto close resource when no longer required.
syntax of try-with-resources
try (//open one or multiple AutoCloseable resources)
{ ......
} catch(Exception e)
{
}



Objective ---To confirm device independence of Java I/O --- replace File device by Console
i.e --- Read data from console i/p --- in buffered manner till 'stop'  & echo back it on the console.



required stream classes --- BR(ISR(System.in))
Alternative is --- use Scanner class.

Adv. of Scanner over above chain ----- contains ready-made parsing methods(eg --- nextInt,nextDouble.....)
But Scanner is not Buffered Stream
Can combine both approaches.(new Scanner(br.readLine())

Objective --- Combine scanner & buffered reader api --- to avail buffering + parsing api. --- 
BufferedReader provides buffering BUT no simple parsing API. -- supplies br.readLine only

Scanner -- Can be attached to file directly
Constr -- Scanner(File f)
BUT no buffering .

How to use both?
Create BR br=new BR(new FR(...));
while ((s=br.readLine())!=null)
 {
    //scanner can be attached to string ---Scanner(String s)
Scanner sc=new Scanner(s);
// parse data using Scanner API --next,nextInt,nextBoolean
}


Overloaded constructor of FileReader(File f)

java.io.File ---- class represents path to file / folder 
Regarding java.io.File -----
Does not follow stream class hierarchy, extends Object directly.
File class --- represents abstract path which can refer to file or folder.
Usage --- 1. To access/check file/folder attributes(exists,file or folder,read/w/exec permisssions,path,parent folder,create new empty file,create tmp files & delete them auto upon termination,mkdir,mkdirs,rename,move,size,last modified ,if folder---list entries from folder,filter entries)
Constructor --- 
File (String path) --- 
eg --- File f1=new File("abc.dat");
if (f1.exists() && f1.isFile() && f1.canRead())
...attach FileInputStream or FileReader

File (String path) --- 
File class API --- boolean exists(),boolean isFile() , boolean canRead()


Objective --- Text File copy operation --- in buffered manner.
For writing data to text file using Buffered streams




java.io.PrintWriter --- char oriented buffered o/p stream --- which can wrap any device.(Binary o/p stream or Char o/p stream)
Constructors---
PrintWriter(Writer w) --- no auto flushing,no conversion, only buffering
PrintWriter(Writer w, boolean flushOnNewLine)--- automatically flush buffer contents on to the writer stream --upon new line 
PrintWriter(OutputStream  w) --- can wrap binary o/p stream -- buffering +conversion(char-->binary),no auto-flush option
PrintWriter(OutputStream w , boolean flushOnNewLine) --- 
API Methods----print/println/printf same as in PrintStream class(same type as System.out)

Stream class which represents --- Char o/p stream connected to Text file. --- java.io.FileWriter
Constructor
FileWriter(String fileName) throws IOException -- new file will be created & data will be written in char format.
FileWriter(String fileName,boolean append) --- if append is true , data will be appended to existing text file.

-----------------
Collection & I/O
Objective ---
Items Inventory 
Item -- code(String-Prim key),desc,category,quantity,price,shipmentDate
constr,toString.

Create suitable collection of Items(HashMap) --- sort map as per desc item code ,& store sorted item dtls in  1 text file . 
NOTE : individual item rec MUST be written on separate line.

Sort items as shipment Date  & store sorted dtls in another file . Before exiting ensure closing of data strms .
(buffered manner)


Objective -- Restore collection of items created in above requirement ---in form of HashMap .  -- buffering is optional.




Objective --- using Binary file streams.
Classes --- FileInputStream -- unbuffered bin i/p stream connected to bin file device.
FileOutputStream --unbuffered bin o/p stream connected to bin file device.

But these classes --- dont provide buffering & have only read() write() methods in units of bytes/byte[]

API of InputStream class
1. int read() throws IOException
Will try to read 1 byte from the stream. 
Data un-available method blocks.
Returns byte--->int to caller.
eg -- int data=System.in.read();
2. int read(byte[] bytes) throws IOException
Will try to read data from underlying stream.
Data un-available -- method blocks.
Rets actual no of bytes read.

eg : 
byte[] bytes=new byte[100];
int no=System.in.read(bytes);
no data --method blocks.
10 bytes available -no =10;bytes[0]-----bytes[9]
110 bytes available -- no=100;bytes[0]....bytes[99]

3. int read(byte[] bytes,int offset,int maxNoOfBytes) throws IOException
Will try to read data from underlying stream.
Data un-available -- method blocks.
Rets actual no of bytes read.
eg : 
byte[] bytes=new byte[100];
int no=System.in.read(bytes,10,15);
no data --BLOCKS
5 bytes available --no=5;bytes[10].....bytes[14]
110 bytes available -- no=15;bytes[10]..bytes[24]

4. int available() throws IOException
Returns no of available bytes in the stream
no data ---DOESN't block -- rets 0.

Important  API of OutputStream
1. public void write(int byte) throws IOException
2. public void write(byte[] bytes) throws IOException
3. public void write(byte[] bytes,int offset,int maxNo) throws IOException
bytes[offset].....bytes[offset+maxNo-1] -- written out to stream
4. void flush() throws IOException
5. void close() throws IOException



Using BIS(BufferedInputStream) -- enables buffering BUT doesn't provide any advanced API(ie. read(), read(byte[]), read(byte[] b,int off,int len) . Same is true with BOS.(BufferedOutputStream)

Objective --- 
Create Customer/Account based collection. Sort if reqd.
Store Sorted collection to bin file in buffered manner -- 
& re-store the same.


Use advanced streams in such cases ---
Mixed Data streams
java.io.DataOutputStream ---implements DataOutput i/f
(converter stream )   prim types / string ---> binary
Constructor -- DataOutputStream (OutputStream out)
API ---
public void writeInt(int i) throws IOExc
public void writeChar(char i) throws IOExc
public void writeFloat,writeDouble.....
For Strings 
public void writeUTF(String s) throws IOExc ---uses Modified UTF 8 convention
or
public void writeChars(String s) throws IOExc --- uses UTF16 convention




eg : Items Inventory 
Item -- code(String-Prim key),desc,category,quantity,price,shipmentDate
constr,toString.



Objective ---
Customer data is already stored in bin file.
Read customer data from Bin file --- in buffered manner & upload the same in HM .display customer details.

Stream class --- java.io.DataInputStream -- implements DataInput
Conversion stream(converts from bin ---> prim type or String)

Constructor
DataInputStream(InputStream in)
API Methods
public int readInt() throws IOException
public double readDouble() throws IOException
public char readChar() throws IOException
public String readUTF() throws IOException(must be used with writeUTF)
public String readChars() throws IOException(must be used with writeChars)


Most Advanced streams ---
Binary streams which can read/write data from/to binary stream in units of Object/Collection of Object refs (i.e Data Transfer Unit = Object/Collection of Objects)

Stream Class for writing Objects to bin. stream
java.io.ObjectOutputStream implements DataOutput,ObjectOutput

Description --- ObjectOutputStream class performs serialization.
serialization= extracting state of object & converting it in binary form.

state of object = non-static & non-transient data members
Constructor 
ObjectOutputStream(OutputStream out) 
out--- dest Binary o/p stream  --- where serialized data stream has to be sent.

API methods ---
public void writeInt(int i) throws IOExc
public void writeChar(char i) throws IOExc
public void writeFloat,writeDouble.....
For Strings 
public void writeUTF(String s) throws IOExc ---uses Modified UTF 8 convention

public void writeObject(Object o) throws IOException,NotSerializableException




De-serialization---- conversion or re-construction of Java objs from bin stream.
java.io.ObjectInputStream  --- performs de-serialization.--- implements DataInput,ObjectInput
Constructor --- ObjectInputStream(InputStream in) 
API methods ---
readInt,readShort,readUTF,readChars..... +
public Object readObject() throws IOException








Objective --- attach OIS to the bin file using FIS & display customer data.

Objective : 
Confirming concepts of serialization & de-serialization
Emp -- int id, String name,double salary,Address adr;
Address -- String state,city,street.

Objective -- Understanding Set & its implementation classes
HashSet -- based upon hashing algorithm



More involved scenario.
(store customer info & Items to be purchased)
Data members - int no,Customer info, AL<Item>, Date creationDate









































