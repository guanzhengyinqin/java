package serializedemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerialTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException
    {
		/*序列化存到硬盘
		Person person = new Person(1234, "wang");
        System.out.println("Person Serial" + person);
        FileOutputStream fos = new FileOutputStream("Person.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.flush();
        oos.close();
		*/
		
		/*反序列化取出数据
        FileInputStream fis = new FileInputStream("d:/Person.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Person> persons = (List<Person>) ois.readObject();
        System.out.println(persons.size());
        ois.close();
        for(Person p:persons){
        	System.out.println(p.name);
        }
        */
        
       // System.out.println("Person Deserial" + persons);
		
    }
}
