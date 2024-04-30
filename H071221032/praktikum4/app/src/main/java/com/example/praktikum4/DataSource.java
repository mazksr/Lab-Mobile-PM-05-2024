package com.example.praktikum4;

import android.icu.util.GregorianCalendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class DataSource {
    static Account currentAccount = new Account("mazksr", "Muhammad Azka",333, 1000, R.drawable.me);

    static Account mufc = new Account("manchesterunited", "Manchester United",1000, 100000, R.drawable.mu_pfp);
    static Post mufc_post = new Post("Bring on Chelsea 👊\n\n#MUFC #ManUtd #Martinez #PremierLeague", R.drawable.mu_post, new GregorianCalendar(2024, 3, 17, 10, 30, 0));
    static Account acm = new Account("acmilan", "AC Milan", 999, 200000, R.drawable.acm_pfp);
    static Post acm_post = new Post("On the same wavelength \uD83D\uDE01\uD83C\uDFC4\n" + "\n\n" + "#FiorentinaMilan #SempreMilan", R.drawable.acm_post, new GregorianCalendar(2024, 3, 17, 10, 31, 0));
    static Account alnassr = new Account("alnassr", "Al Nassr FC", 51, 50000, R.drawable.alnassr_pfp);
    static Post alnassr_post = new Post("SIUUUU", R.drawable.alnassr_post, new GregorianCalendar(2024, 3, 17, 10, 32, 0));
    static Account madrid = new Account("realmadrid", "Real Madrid FC",90, 888880, R.drawable.madrid_pfp);
    static Post madrid_post = new Post("\uD83D\uDCC6 #Madridistas, here’s our April calendar! \n" +
            "\uD83E\uDD29 ¡Madridistas, así se presenta nuestro mes de abril! \n" +
            "@Visit.Dubai | #VisitDubai", R.drawable.madrid_post, new GregorianCalendar(2024, 3, 17, 10, 33, 0));
    static Account arsenal = new Account("arsenal", "Arsenal FC",700, 1287391, R.drawable.arsenal_pfp);
    static Post arsenal_post = new Post("We keep moving \uD83D\uDCAA", R.drawable.arsenal_post, new GregorianCalendar(2024, 3, 17, 10, 34, 0));
    static Account atm = new Account("atleticodemadrid", "Atletico de Madrid FC",123, 831283, R.drawable.atm_pfp);
    static Post atm_post = new Post("Facing Bayern!", R.drawable.atm_post, new GregorianCalendar(2024, 3, 17, 10, 35, 0));

    static ArrayList<Account> accounts;
    public static ArrayList<Account> generateDummyAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();

        accounts.add(currentAccount);
        accounts.add(mufc);
        accounts.add(acm);
        accounts.add(alnassr);
        accounts.add(madrid);
        accounts.add(arsenal);
        accounts.add(atm);


        acm.createPost(acm_post);
        mufc.createPost(mufc_post);
        alnassr.createPost(alnassr_post);
        madrid.createPost(madrid_post);
        arsenal.createPost(arsenal_post);
        atm.createPost(atm_post);

        return accounts;

    }

    static ArrayList<Post> allPost = new ArrayList<>();
//    private static ArrayList<Post> combinePosts() {
//        System.out.println(currentAccount.getObjectId());
//        ArrayList<Post> posts1 = new ArrayList<>();
//        for (int i = 0;i<accounts.size();i++) {
//            posts1.addAll(accounts.get(i).getPosts());
//        }
//        Collections.sort(posts1, new Comparator<Post>() {
//            @Override
//            public int compare(Post o1, Post o2) {
//                return o2.getPostedDate().compareTo(o1.getPostedDate());
//            }
//        });
//
//        return posts1;
//    }

}


