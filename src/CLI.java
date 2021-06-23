import ui.controller.beobachter.*;

public class CLI {
    public static void main(String[] args) {
          ConsoleSubjekt s = new ConsoleSubjekt();
//        ConsoleBeobachterExit b = new ConsoleBeobachterExit(s);
//        new ConsoleBeobachterAdd(s);
//        new ConsoleBeobachterHelp(s);
//        new ConsoleBeobachterShow(s);
//        new ConsoleBeobachterRemove(s);
//
//        s.start()
         FastVollBeobachter beobachter = new FastVollBeobachter(s);

         s.start();
    }
}
