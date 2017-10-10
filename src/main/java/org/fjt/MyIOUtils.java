package org.fjt;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyIOUtils {

    private PrintStream savedStdout;
    private PrintStream savedStderr;

    public MyIOUtils() {
        this.savedStdout = System.out;
        this.savedStderr = System.err;
    }
    
    public void redirectStdoutToNULL() {
        try {
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    // Do nothing.
                }
            }));
        } catch (Exception ex) {
            Logger.getLogger(Odata.class.getName()).log(Level.SEVERE, null, ex);
            System.setOut(this.savedStderr);
        }
    }
    
    public void redirectStdout(String filename, Boolean append) {
        if (filename.isEmpty() == false) {
            try {
                System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(filename, append)), true));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Odata.class.getName()).log(Level.SEVERE, null, ex);
                System.setOut(this.savedStdout);
            }
        }
    }

    public void restoreStdout() {
        if (System.out != this.savedStdout) {
            // We were writing to a file.
            System.out.flush();
            System.out.close();
        }
        System.setOut(this.savedStdout);
    }

    public void redirectStderrToNULL() {
        try {
            System.setErr(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    // Do nothing.
                }
            }));
        } catch (Exception ex) {
            Logger.getLogger(Odata.class.getName()).log(Level.SEVERE, null, ex);
            System.setErr(this.savedStderr);
        }
    }

    public void redirectStderr(String filename, Boolean append) {
        if (filename.isEmpty() == false) {
            try {
                System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream(filename, append)), true));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Odata.class.getName()).log(Level.SEVERE, null, ex);
                System.setErr(this.savedStderr);
            }
        }
    }

    public void restoreStderr() {
        if (System.err != this.savedStderr) {
            // We were writing to a file.
            System.err.flush();
            System.err.close();
        }
        System.setErr(this.savedStderr);
    }
    

}
