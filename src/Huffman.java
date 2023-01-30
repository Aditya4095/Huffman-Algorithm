//import com.sun.source.tree.BinaryTree;
//import jdk.incubator.vector.VectorOperators;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
 public class Huffman {
  public static void main(String[] args) throws FileNotFoundException {

   Scanner in = new Scanner(System.in);
   Queue<BinaryTree<Pair>> S= new LinkedList<>();
   Queue<BinaryTree<Pair>> T= new LinkedList<>();

   System.out.print("Enter the name of the file with letters and probability: ");

   //Taking input from by specifying the file location
   String inputfile = in.next();
   File inpf = new File(inputfile);

   Scanner scan = new Scanner(inpf);
   Scanner kb = new Scanner(System.in);
   String c = kb.nextLine();
   BinaryTree<Pair> tree = new BinaryTree<>(); 
   Pair p = new Pair();

   //
   while (scan.hasNext()){
    tree = new BinaryTree<>();
    String s = scan.next();
    Double prob  = scan.nextDouble();
    tree.makeRoot(new Pair(s.charAt(0),prob));  //Storing the value and proob at the root of the tree as a pair
    S.add(tree);


   }

   BinaryTree<Pair> ans = HuffT(S,T); // Making the Huffman tree
   Character[] characters = new Character[26];
   Character ch = 'A';
   for (int i = 0; i < 26; i++) {

         characters[i] = ch;
         ch++;
   }

   String[] answer = findEncoding(ans); //Passing the huffman tree to findEncoding method

   HashMap<Character,String> hash = new HashMap<>();

   for (int i = 0; i < 26; i++) {
      hash.put(characters[i],answer[i]);
   }

   String[] out  = new String[c.length()];
   for (int i = 0; i < c.length(); i++) {

    for (char j = 'A'; j <= 'Z'; j++) {
        if(c.charAt(i) == j){
         out[i] = hash.get(j);
        }

        if(c.charAt(i)==' '){
         out[i] = " ";
        }
    }
   }

   for (int i = 0; i < out.length; i++) {
    System.out.print(out[i]);
   }

  }

  /**
   *
   * @param S Queue S of type Binary Tree
   * @param T Queue T of type Binary Tree
   * @return Binary Tree<Pair>
   */

  public static BinaryTree<Pair> HuffT(Queue<BinaryTree<Pair>> S , Queue<BinaryTree<Pair>> T) {

   BinaryTree<Pair> A = new BinaryTree<>(); //binary tree A
   BinaryTree<Pair> B = new BinaryTree<>(); //binary tree B

   // Running the loop while S is not empty
   while (!S.isEmpty()) {

    if (T.isEmpty()) {
     A = S.remove(); //Deque A
     B = S.remove(); //Deque B
    }
    if(!T.isEmpty()) {
     // Finding if the first element of S is smaller or equal then first element in T
     if (S.peek().getData().getProb() <= T.peek().getData().getProb()) {
      A = S.remove(); // Deque S
     }
     // Finding if the first element of S is greater then first element in T
     else if (S.peek().getData().getProb() > T.peek().getData().getProb()) {
      A = T.remove(); // Deque T
     }
     // Checking if T is empty and S is not empty
     if (T.isEmpty() && !S.isEmpty()) {
      B = S.remove();
     }
     // // Checking if T is not empty and S is empty
     else if (!T.isEmpty() && S.isEmpty()) {
      B = T.remove();
     }
     else {

      if (S.peek().getData().getProb() <= T.peek().getData().getProb()) {
       B = S.remove();
      }
      else if (S.peek().getData().getProb() > T.peek().getData().getProb()) {
       B = T.remove();
      }
     }
    }

    BinaryTree<Pair> P = new BinaryTree<>();

    // Storing the combine weight of probabilties of both the trees in varibale
    double combw = A.getData().getProb() + B.getData().getProb();
    Pair p = new Pair('0', combw); //Making a new pair
    P.makeRoot(p); //new pair as root
    P.attachLeft(A);
    P.attachRight(B);
    T.add(P);
   }
    while (T.size() > 1) {
     BinaryTree<Pair> pt = new BinaryTree<>();
     A = T.remove();
     B = T.remove();


     double comb = A.getData().getProb() + B.getData().getProb();
     Pair pair = new Pair('0', comb);
     pt.makeRoot(pair);
     pt.attachLeft(A);
     pt.attachRight(B);
     T.add(pt);

    }



   return T.peek(); // Returns the head of the queue

  }
  private static String[] findEncoding(BinaryTree<Pair> bt){
   String[] result = new String[26];
   findEncoding(bt, result, "");
   return result;
  }
  private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix){
   // test is node/tree is a leaf
   if (bt.getLeft()==null && bt.getRight()==null){
    a[bt.getData().getValue() - 65] = prefix;
   }
   // recursive calls
   else{
    findEncoding(bt.getLeft(), a, prefix+"0");
    findEncoding(bt.getRight(), a, prefix+"1");
   }
  }

 }
