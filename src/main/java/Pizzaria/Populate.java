package Pizzaria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;

public class Populate {



    private void bordas(Session session) {
        List<Borda> bordas1 = session.createQuery("select borda from Borda borda", Borda.class).getResultList();

        String [] a = {"Catupiry", "Cheddar", "Chocolate preto", "Chocolate branco", "Mussarela"};

        boolean resul1 = true;


        int c;

        if (a.length > bordas1.size()) {
            c = bordas1.size();
        } else {
            c = a.length;
        }

        for (int i = 0; i < c; i++) {
            if (Objects.equals(bordas1.get(i).getSabor(), a[i])) {
                resul1 = false;
            }
        }

        if (resul1) {
            session.persist(new Borda("Catupiry", 23));
            session.persist(new Borda("Cheddar", 24));
            session.persist(new Borda("Chocolate preto", 23));
            session.persist(new Borda("Chocolate branco", 24));
            session.persist(new Borda("Mussarela", 23));
        }
    }




    public void start (SessionFactory sessionFactory) {

        sessionFactory.inTransaction(session -> {
            bordas(session);
        });


        sessionFactory.inTransaction(session -> {
            Sabor sabor = new Sabor("Ouro Branco",90.0,"Mussarela, Leite Condensado, Chocolate Branco, Bom-Bom Ouro Branco e Cerejas");

            session.persist(sabor);
        });

    }

}
