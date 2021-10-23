import java.io.*;
import java.rmi.*;

public class FileClient{

    static String upload = "upload";
	static String download = "download";
public static void main(String argv[]) {
if(argv.length != 3) {

    System.out.println("Usage: java FileClient fileName machineName download|upload ");
    System.exit(0);
}

try {
    if(download.equals(argv[2]))
{

    String name = "//" + argv[1] + "/FileServer";
    FileInterface fi = (FileInterface) Naming.lookup(name);
    System.out.println("Client Ready....remote stub active");
    byte[] filedata = fi.downloadFile(argv[0]);
    File file = new File(argv[0]);
    BufferedOutputStream output = new
    BufferedOutputStream(new FileOutputStream(file.getName()));
    output.write(filedata,0,filedata.length);
    System.out.println("The file downloaded successfully");
    output.flush();
    output.close();
}
    else if (upload.equals(argv[2]))
{

    String name = "//" + argv[1] + "/FileServer";
    FileInterface fi = (FileInterface) Naming.lookup(name);
    System.out.println("Client Ready....remote stub active");
    File file = new File(argv[0]);
    byte buffer[] = new byte[(int)file.length()];
    BufferedInputStream input = new
    BufferedInputStream(new FileInputStream(file.getName()));
    input.read(buffer,0,buffer.length);
    System.out.println("The file was uploaded successfully");
    input.close();
    fi.uploadFile(argv[0],buffer);
}

} catch(Exception e) {
    System.err.println("FileServer exception: "+ e.getMessage());
    e.printStackTrace();
}
}
} 
