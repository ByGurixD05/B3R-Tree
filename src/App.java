import main.*;

public class App {
    public static void main(String[] args) throws Exception {
        B3RTree b =new B3RTree();
        
        for(int i = 0; i<16; i++){
            b.insert(i);
            System.out.println(b.toString());

        }

        
    }
}
