package com.anggasaraya.moviecatalogue.helper

import com.anggasaraya.moviecatalogue.R
import com.anggasaraya.moviecatalogue.data.MovieEntity
import com.anggasaraya.moviecatalogue.data.TVShowEntity

object DataDummy {
    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(MovieEntity(
                "1m",
                "05/10/2018",
                "A Star Is Born",
                "Drama, Percintaan, Musik",
                "75%",
                "Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
                "Rilis",
                "Inggris",
                "\$36,000,000.00",
                "\$433,888,866.00",
                R.drawable.poster_a_start_is_born
        ))
        movies.add(MovieEntity(
                "2m",
                "14/02/2019",
                "Alita: Battle Angel",
                "Aksi, Cerita Fiksi, Petualangan",
                "72%",
                "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.",
                "Rilis",
                "Inggris",
                "\$170,000,000.00",
                "\$404,852,543.00",
                R.drawable.poster_alita
        ))
        movies.add(MovieEntity(
                "3m",
                "14/02/2019",
                "Aquaman",
                "Aksi, Petualangan, Fantasi",
                "69%",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Rilis",
                "Inggris",
                "\$160,000,000.00",
                "\$1,148,461,807.00",
                R.drawable.poster_aquaman
        ))
        movies.add(MovieEntity(
                "4m",
                "02/11/2018",
                "Bohemian Rhapsody",
                "Musik, Drama, Sejarah",
                "80%",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Rilis",
                "Inggris",
                "\$52,000,000.00",
                "\$894,027,543.00",
                R.drawable.poster_bohemian
        ))
        movies.add(MovieEntity(
                "5m",
                "08/02/2019",
                "Cold Pursuit",
                "Aksi, Kejahatan, Cerita Seru",
                "57%",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Rilis",
                "Inggris",
                "\$60,000,000.00",
                "\$76,419,755.00",
                R.drawable.poster_cold_persuit
        ))
        movies.add(MovieEntity(
                "6m",
                "21/11/2018",
                "Creed II",
                "Drama",
                "69%",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Rilis",
                "Inggris",
                "\$50,000,000.00",
                "\$214,215,889.00",
                R.drawable.poster_creed
        ))
        movies.add(MovieEntity(
                "7m",
                "16/11/2018",
                "Fantastic Beasts: The Crimes of Grindelwaid",
                "Petualangan, Fantasi, Drama",
                "69%",
                "Gellert Grindelwald telah melarikan diri dari penjara dan telah mulai mengumpulkan pengikut ke tujuannya — meninggikan penyihir di atas semua makhluk non-magis. Satu-satunya yang bisa menghentikannya adalah penyihir yang pernah disebutnya sebagai sahabat terdekatnya, Albus Dumbledore. Namun, Dumbledore akan perlu mencari bantuan dari penyihir yang telah menggagalkan Grindelwald sebelumnya, mantan muridnya, Newt Scamander, yang setuju untuk membantu, tidak menyadari bahaya yang ada di depan. Garis-garis digambar saat cinta dan kesetiaan diuji, bahkan di antara teman-teman dan keluarga sejati, di dunia sihir yang semakin terbagi.",
                "Rilis",
                "Inggris",
                "\$200,000,000.00",
                "\$653,355,901.00",
                R.drawable.poster_crimes
        ))
        movies.add(MovieEntity(
                "8m",
                "18/01/2019",
                "Glass",
                "Cerita Seru, Drama, Cerita Fiksi",
                "67%",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "Rilis",
                "Inggtid",
                "\$20,000,000.00",
                "\$246,941,965.00",
                R.drawable.poster_glass
        ))
        movies.add(MovieEntity(
                "9m",
                "09/01/2019",
                "How to Train Your Dragon: The Hidden World",
                "Animasi, Keluarga, Petualangan",
                "78%",
                "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
                "Rilis",
                "Inggris",
                "\$129,000,000.00",
                "\$517,526,875.00",
                R.drawable.poster_how_to_train
        ))
        movies.add(MovieEntity(
                "10m",
                "27/04/2018",
                "Avengers: Infinity War",
                "Petualangan, Aksi, Cerita Fiksi",
                "83%",
                "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.",
                "Rilis",
                "Inggris",
                "\$300,000,000.00",
                "\$2,046,239,637.00",
                R.drawable.poster_infinity_war
        ))
        return movies
    }
    fun generateDummyTVShows(): ArrayList<TVShowEntity> {
        val tvShows = ArrayList<TVShowEntity>()
        tvShows.add(TVShowEntity(
                "1t",
                "Oktober 10, 2012",
                "The Arrow",
                "Kejahatan, Drama, Misteri, Aksi & Petualangan",
                "66%",
                "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
                "Berakhir",
                "Inggris",
                R.drawable.poster_arrow
        ))
        tvShows.add(TVShowEntity(
                "2t",
                "Februari 15, 2019",
                "Doom Patrol",
                "Sci-fi & Fantasy, Komedi, Drama",
                "76%",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Berlanjut",
                "Inggris",
                R.drawable.poster_doom_patrol
        ))
        tvShows.add(TVShowEntity(
                "3t",
                "Februari 26, 1986",
                "ドラゴンボール ",
                "Animasi, Aksi & Petualangan, Sci-fi & Fantasy",
                "81%",
                "Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.",
                "Berakhir",
                "Jepang",
                R.drawable.poster_dragon_ball
        ))
        tvShows.add(TVShowEntity(
                "4t",
                "Oktober 12, 2009",
                "フェアリーテイル",
                "Aksi & Petualangan, Animasi, Komedi, Sci-fi & Fantasy, Misteri",
                "78%",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "Berakhir",
                "Jepang",
                R.drawable.poster_fairytail
        ))
        tvShows.add(TVShowEntity(
                "5t",
                "Januari 31, 1999",
                "Family Guy",
                "Animasi, Komedi",
                "70%",
                "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.",
                "Berlanjut",
                "Inggris",
                R.drawable.poster_family_guy
        ))
        tvShows.add(TVShowEntity(
                "6t",
                "Oktober 7, 2014",
                "The Flash",
                "Drama, Sci-fi & Fantasy\n",
                "77%",
                "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya \"manusia meta\" yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
                "Berlanjut",
                "Inggris",
                R.drawable.poster_flash
        ))
        tvShows.add(TVShowEntity(
                "7t",
                "April 17, 2011",
                "Game of Thrones",
                "Sci-fi & Fantasy, Drama, Aksi & Petualangan",
                "84%",
                "Tujuh keluarga bangsawan berjuang untuk menguasai tanah mitos Westeros. Gesekan antara rumah-rumah mengarah ke perang skala penuh. Semua sementara kejahatan yang sangat kuno terbangun di utara terjauh. Di tengah-tengah perang, perintah militer yang diabaikan, Night's Watch, adalah yang berdiri di antara alam manusia dan kengerian es di luarnya.",
                "Berakhir",
                "Inggris",
                R.drawable.poster_god
        ))
        tvShows.add(TVShowEntity(
                "8t",
                "September 22, 2014",
                "Gotham",
                "Drama, Kejahatan, Sci-fi & Fantasy\n",
                "75%",
                "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka - persona yang lebih besar dari kehidupan yang akan menjadi Catwoman, The Penguin, The Riddler, Two-Face dan The Joker?",
                "Berakhir",
                "Inggris",
                R.drawable.poster_gotham
        ))
        tvShows.add(TVShowEntity(
                "9t",
                "Maret 28, 2019",
                "Hanna",
                "Aksi & Petualangan, Drama",
                "75%",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "Berlanjut",
                "Inggris",
                R.drawable.poster_hanna
        ))
        tvShows.add(TVShowEntity(
                "10t",
                "Maret 17, 2017",
                "Marvel's Iron Fist",
                "Aksi & Petualangan, Drama, Sci-fi & Fantasy",
                "66%",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Dibatalkan",
                "Inggris",
                R.drawable.poster_iron_fist
        ))
        return tvShows
    }
}