import java.io.*; 
import java.net.*; 
public class Sender{ 
Socket sender; 
ObjectOutputStream output; ObjectInputStream input; String packet,acknowlwdge; 
String s, mes; 
int len; 
int a=0,seq=0; 
Sender(){} 
public void set(){ 
try
10 
{ 
BufferedReader reader=new BufferedReader(new InputStreamReader(System.in)); System.out.println("Waiting for the Connection...."); 
sender = new Socket("localhost",2022); 
seq=0; 
output=new ObjectOutputStream(sender.getOutputStream()); output.flush(); 
input=new ObjectInputStream(sender.getInputStream()); 
s=(String)input.readObject(); 
System.out.println("reciver side> "+s); 
System.out.println("Enter the data to be send to receiver...."); packet=reader.readLine(); 
len=packet.length(); 
do{ 
try{ 
if(a<len){ 
mes=String.valueOf(seq); 
mes=mes.concat(packet.substring(a,a+1)); 
} 
else if(a==len){ 
mes="end"; 
output.writeObject(mes); 
break;
11 
} 
output.writeObject(mes); 
if(seq==0){ 
seq=1 
} 
else 
seq=0 
output.flush(); 
System.out.println("data sent>"+mes); 
acknowlwdge=(String)input.readObject(); 
System.out.println("waiting for ack.....\n\n"); if(acknowlwdge.equals(String.valueOf(seq))){ a++; 
System.out.println("receiver side> "+" packet recieved\n\n"); } 
else{ 
System.out.println("Time out resending data....\n\n"); if(seq==0){ 
seq=1 
} 
else 
seq=0 
} 
}
12 
catch(Exception e){} 
} 
while(a<len+1); 
System.out.println("All data sent. "); 
System.out.println("\nExisting from the data paket. "); } 
catch(Exception e){} 
finally{ 
try{ 
input.close(); 
output.close(); 
sender.close(); 
} 
catch(Exception e){} 
} 
} 
public static void main(String args[]){ Sender sen=new Sender(); 
sen.set(); 
} 
}
