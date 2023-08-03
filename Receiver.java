import java.io.*; 
import java.net.*; 
public class Receiver{ 
ServerSocket reciever; 
Socket connection=null; 
ObjectOutputStream output; 
ObjectInputStream input; 
String packet,acknowledge; 
String data=" "; 
int a=0,seq=0; 
Receiver() 
{ 
} 
public void get(){ 
try{ 
BufferedReader reader=new BufferedReader(new InputStreamReader(System.in)); reciever = new ServerSocket(2022,12); 
System.out.println("waiting for the connection..."); 
connection=reciever.accept(); 
seq=0; 
System.out.println("Connection is established :"); 
output=new ObjectOutputStream(connection.getOutputStream());
14 
output.flush(); 
input=new ObjectInputStream(connection.getInputStream()); output.writeObject("data is connected."); 
do{ 
try{ 
packet=(String)input.readObject(); 
if(Integer.valueOf(packet.substring(0,1))==seq){ data=data+packet.substring(1); 
if(seq==0){ 
seq=1 
} 
else 
seq=0 
System.out.println("\n\nreceiver side >"+packet); 
} 
else 
{ 
System.out.println("\n\nreceiver side >"+packet +" duplicate data apcket"); } 
if(a<3){ 
output.writeObject(String.valueOf(seq)); 
a++; 
} 
else{
15 
output.writeObject(String.valueOf((seq+1)%2)); a=0; 
} 
} 
catch(Exception e){} 
}while(!packet.equals("End")); 
System.out.println("Data recived is="+data); output.writeObject("connection is ended ."); } 
catch(Exception e){} 
finally{ 
try{ 
input.close(); 
output.close(); 
reciever.close(); 
} 
catch(Exception e){} 
} 
} 
public static void main(String args[]){ Receiver rec=new Receiver(); while(true){ 
rec.get(); 
}


} 
} 
