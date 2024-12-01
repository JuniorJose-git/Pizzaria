package Pizzaria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;


// sa o banco de dados estiver vazio adiciona esses valores
public class Populate {

    private void bordas(Session session) {

        if (session.createQuery("select borda from Borda borda", Borda.class).getResultList().isEmpty()) {
            session.persist(new Borda("Catupiry", 23));
            session.persist(new Borda("Cheddar", 24));
            session.persist(new Borda("Chocolate preto", 23));
            session.persist(new Borda("Chocolate branco", 24));
            session.persist(new Borda("Mussarela", 23));
        }
    }

    private void sabor ( Session session) {

        if ( session.createQuery("from Sabor", Sabor.class).getResultList().isEmpty()) {
            session.persist(new Sabor("Ouro Branco",90.0,"Mussarela, Leite Condensado, Chocolate Branco, Bom-Bom Ouro Branco e Cerejas","doce"));
            session.persist(new Sabor("Romeu e Julieta",90.0,"Mussarela, Goiabada e Leite Condensado","doce"));
            session.persist(new Sabor("Peperroni",90.0,"Massa de pizza, Molho de tomate, Queijo mussarela","salgado"));
            session.persist(new Sabor("Frango Catupiry",90.0,"Mussarela, Frango Desfiado, Catupiry, Azeitona e Oregano","salgado"));
            session.persist(new Sabor("Calabresa",90.0,"Mussarela, Cebola, Azeitona, Oregano","salgado"));
            session.persist(new Sabor("Baiana",90.0,"Mussarela, Presunto, Calabresa, Pimenta, Cebola, Azeitona e Oregano","salgado"));
            session.persist(new Sabor("Margarita",90.0,"Mussarela, tomate, Azeitona e Margericão","salgado"));
            session.persist(new Sabor("4 Queijos",90.0,"Mussarela, Catupiry, Provolone, Parmesão, Azeitona e Oregano","salgado"));
        }

    }

    private void tamanho (Session session) {

        if (session.createQuery("from Tamanho", Tamanho.class).getResultList().isEmpty()) {
            session.persist(new Tamanho(12));
            session.persist(new Tamanho(8));
            session.persist(new Tamanho(4));
        }
    }

    public void start (SessionFactory sessionFactory) {

        sessionFactory.inTransaction(session -> {
            bordas(session);
            sabor(session);
            tamanho(session);
        });

    }

}
