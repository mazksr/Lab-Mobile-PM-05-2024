package com.example.praktikum3;

import java.util.ArrayList;

public class DataSource {
    static Account mufc = new Account("manchesterunited", 1000, 100000, R.drawable.mu_pfp);
    static Post mufc_post = new Post(mufc, "Bring on Chelsea 👊\n\n#MUFC #ManUtd #Martinez #PremierLeague", R.drawable.mu_post, R.drawable.mu_story);
    static Account acm = new Account("acmilan", 999, 200000, R.drawable.acm_pfp);
    static Post acm_post = new Post(acm, "On the same wavelength \uD83D\uDE01\uD83C\uDFC4\n" + "\n\n" + "#FiorentinaMilan #SempreMilan", R.drawable.acm_post, R.drawable.acm_story);
    static Account alnassr = new Account("alnassr", 51, 50000, R.drawable.alnassr_pfp);
    static Post alnassr_post = new Post(alnassr, "SIUUUU", R.drawable.alnassr_post, R.drawable.alnassr_story);
    static Account madrid = new Account("realmadrid", 90, 888880, R.drawable.madrid_pfp);
    static Post madrid_post = new Post(madrid, "\uD83D\uDCC6 #Madridistas, here’s our April calendar! \n" +
            "\uD83E\uDD29 ¡Madridistas, así se presenta nuestro mes de abril! \n" +
            "@Visit.Dubai | #VisitDubai", R.drawable.madrid_post, R.drawable.madrid_story);
    static Account arsenal = new Account("arsenal", 700, 1287391, R.drawable.arsenal_pfp);
    static Post arsenal_post = new Post(arsenal, "We keep moving \uD83D\uDCAA", R.drawable.arsenal_post, R.drawable.arsenal_story);
    static Account atm = new Account("atleticodemadrid", 123, 831283, R.drawable.atm_pfp);
    static Post atm_post = new Post(atm, "Facing Bayern!", R.drawable.atm_post, R.drawable.atm_story);
    static Account bayer = new Account("bayer04fussball", 4232, 791928, R.drawable.bayer_pfp);
    static Post bayer_post = new Post(bayer, "\uD835\uDE52\uD835\uDE5A \uD835\uDE59\uD835\uDE64\uD835\uDE63'\uD835\uDE69 \uD835\uDE68\uD835\uDE69\uD835\uDE64\uD835\uDE65! \uD83D\uDDA4❤\uFE0F\n" +
            "\n" +
            "#Bayer04 #Werkself", R.drawable.bayer_post, R.drawable.bayer_story);
    static Account bvb = new Account("bvb09", 1432, 15000, R.drawable.bvb_pfp);
    static Post bvb_post = new Post(bvb, "\uD83E\uDD70 Wochenstart like this! Und \uD835\uDC09\uD835\uDC04\uD835\uDC13\uD835\uDC19\uD835\uDC13: Blicke schärfen auf eine besondere \uD83C\uDF82 Geburtstags-Woche und das Heimspiel gegen den VfB Stuttgart! \uD83D\uDC40"
            , R.drawable.bvb_post, R.drawable.bvb_story);
    static Account juve = new Account("juventus", 827, 91299, R.drawable.juve_pfp);
    static Post juve_post = new Post(juve, "Striking duo \uD83E\uDD1C\uD83E\uDD1B", R.drawable.juve_post, R.drawable.juve_story);
    static Account psg = new Account("psg", 374, 2812882, R.drawable.psg_pfp);
    static  Post psg_post = new Post(psg, "\uD83D\uDD1D✅\n" +
            "\n" +
            "200 appearances in Ligue 1 with Paris for Kylian Mbappé! \uD83D\uDD25\n" +
            "\n" +
            "Kylian Mbappé atteint le cap des 200 apparitions en Ligue 1 avec le Paris Saint-Germain ! \uD83D\uDD25"
            , R.drawable.psg_post, R.drawable.psg_story);

    public static ArrayList<Account> accounts = generateDummyAccounts();
    public static ArrayList<Post> posts = generateDummyPosts(accounts);

    private static ArrayList<Account> generateDummyAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(mufc);
        accounts.add(acm);
        accounts.add(alnassr);
        accounts.add(madrid);
        accounts.add(arsenal);
        accounts.add(atm);
        accounts.add(bayer);
        accounts.add(bvb);
        accounts.add(juve);
        accounts.add(psg);
        return accounts;

    }

    private static ArrayList<Post> generateDummyPosts(ArrayList<Account> accounts) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(mufc_post);
        posts.add(acm_post);
        posts.add(alnassr_post);
        posts.add(madrid_post);
        posts.add(arsenal_post);
        posts.add(atm_post);
        posts.add(bayer_post);
        posts.add(bvb_post);
        posts.add(juve_post);
        posts.add(psg_post);
        return posts;
    }
}


