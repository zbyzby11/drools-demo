package cn.edu.seu.dynamic.service.drools;

import com.idealista.tlsh.TLSH;

//import com.

public class test {
    public static void main(String[] args) {
        // Quote extracted from 'The UNIX-HATERS Handbook'
        String data = "The best documentation is the UNIX source. After all, this is what the "
                + "system uses for documentation when it decides what to do next! The "
                + "manuals paraphrase the source code, often having been written at "
                + "different times and by different people than who wrote the code. "
                + "Think of them as guidelines. Sometimes they are more like wishes... "
                + "Nonetheless, it is all too common to turn to the source and find "
                + "options and behaviors that are not documented in the manual. Sometimes "
                + "you find options described in the manual that are unimplemented "
                + "and ignored by the source.";

        System.out.println(new TLSH(data).hash());

        System.out.println(new TLSH(data + "a").hash());

    }
}
