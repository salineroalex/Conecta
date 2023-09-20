package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/**
 *
 * @author alexs, iratig
 */
public class MyObjectOutputStream extends ObjectOutputStream{
    //Method to rewrite the file header.
    @Override
    protected void writeStreamHeader() throws IOException{
        reset();
    }
    //Constructors
    public MyObjectOutputStream () throws IOException{
        super();
    }
    public MyObjectOutputStream(FileOutputStream out) throws IOException{
        super(out);
    }
}
