PGDMP     9                     v         	   PandaFlix    9.6.5    9.6.5 ?    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    24597 	   PandaFlix    DATABASE     }   CREATE DATABASE "PandaFlix" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE "PandaFlix";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12427    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24788    ActorInFilm    TABLE     Y   CREATE TABLE "ActorInFilm" (
    "Actor" text NOT NULL,
    "FilmID" integer NOT NULL
);
 !   DROP TABLE public."ActorInFilm";
       public         postgres    false    3            �            1259    24801    ActorInTVSerie    TABLE     _   CREATE TABLE "ActorInTVSerie" (
    "Actor" text NOT NULL,
    "TVSerieID" integer NOT NULL
);
 $   DROP TABLE public."ActorInTVSerie";
       public         postgres    false    3            �            1259    24631    Address    TABLE     �   CREATE TABLE "Address" (
    "Street" text NOT NULL,
    "Country" text NOT NULL,
    "ZipCode" integer NOT NULL,
    "District" text NOT NULL,
    "User" text NOT NULL
);
    DROP TABLE public."Address";
       public         postgres    false    3            �            1259    24864    Advice    TABLE     �   CREATE TABLE "Advice" (
    "Adviser" text NOT NULL,
    "Advisor" text NOT NULL,
    "IDFilm" integer NOT NULL,
    "IDTVSerie" integer NOT NULL,
    "isFilm" boolean NOT NULL
);
    DROP TABLE public."Advice";
       public         postgres    false    3            �            1259    24764    Cart    TABLE     �   CREATE TABLE "Cart" (
    "User" text NOT NULL,
    "FilmID" integer NOT NULL,
    "Quantity" integer NOT NULL,
    "TVSerieID" integer NOT NULL
);
    DROP TABLE public."Cart";
       public         postgres    false    3            �            1259    24657    Episode    TABLE     �   CREATE TABLE "Episode" (
    "Path" text NOT NULL,
    "TVSerie" integer NOT NULL,
    episode integer NOT NULL,
    season integer NOT NULL
);
    DROP TABLE public."Episode";
       public         postgres    false    3            �            1259    24832 	   Favourite    TABLE     �   CREATE TABLE "Favourite" (
    "User" text NOT NULL,
    "IDMultimedia" integer NOT NULL,
    "isFilm" boolean NOT NULL,
    "IDSerie" integer NOT NULL
);
    DROP TABLE public."Favourite";
       public         postgres    false    3            �            1259    24607    Film    TABLE       CREATE TABLE "Film" (
    "ID" integer NOT NULL,
    "Title" text NOT NULL,
    "Category" text NOT NULL,
    "Year" integer,
    "Director" text,
    "Trailer" text NOT NULL,
    "VideoOnDemand" text NOT NULL,
    "Plot" text,
    "Price" double precision NOT NULL,
    "Image" text
);
    DROP TABLE public."Film";
       public         postgres    false    3            �            1259    24814 
   Friendship    TABLE     �   CREATE TABLE "Friendship" (
    "User1" text NOT NULL,
    "User2" text NOT NULL,
    accepted boolean DEFAULT false NOT NULL
);
     DROP TABLE public."Friendship";
       public         postgres    false    3            �            1259    24670    PaymentMethod    TABLE     �   CREATE TABLE "PaymentMethod" (
    "CardNumber" text NOT NULL,
    "User" text NOT NULL,
    "Code" integer NOT NULL,
    "ExpirationDate" date NOT NULL
);
 #   DROP TABLE public."PaymentMethod";
       public         postgres    false    3            �            1259    24683    RankingFilm    TABLE     s   CREATE TABLE "RankingFilm" (
    "Rank" integer NOT NULL,
    "User" text NOT NULL,
    "Film" integer NOT NULL
);
 !   DROP TABLE public."RankingFilm";
       public         postgres    false    3            �            1259    24701    RankingTVSerie    TABLE     y   CREATE TABLE "RankingTVSerie" (
    "Rank" integer NOT NULL,
    "User" text NOT NULL,
    "TVSerie" integer NOT NULL
);
 $   DROP TABLE public."RankingTVSerie";
       public         postgres    false    3            �            1259    24615    TVSerie    TABLE     /  CREATE TABLE "TVSerie" (
    "ID" integer NOT NULL,
    "Title" text NOT NULL,
    "Category" text NOT NULL,
    "Director" text,
    "Year" integer,
    "Completed" boolean,
    "Seasons" integer,
    "Trailer" text NOT NULL,
    "Plot" text,
    "Price" double precision NOT NULL,
    "Image" text
);
    DROP TABLE public."TVSerie";
       public         postgres    false    3            �            1259    24623    User    TABLE     �   CREATE TABLE "User" (
    "Password" text NOT NULL,
    "E-Mail" text NOT NULL,
    "Premium" boolean NOT NULL,
    "Admin" boolean NOT NULL,
    "Date" date,
    "FirstName" text NOT NULL,
    "LastName" text NOT NULL,
    "Image" text
);
    DROP TABLE public."User";
       public         postgres    false    3            �          0    24788    ActorInFilm 
   TABLE DATA               3   COPY "ActorInFilm" ("Actor", "FilmID") FROM stdin;
    public       postgres    false    194   �G       �          0    24801    ActorInTVSerie 
   TABLE DATA               9   COPY "ActorInTVSerie" ("Actor", "TVSerieID") FROM stdin;
    public       postgres    false    195   �G       �          0    24631    Address 
   TABLE DATA               P   COPY "Address" ("Street", "Country", "ZipCode", "District", "User") FROM stdin;
    public       postgres    false    188   H       �          0    24864    Advice 
   TABLE DATA               R   COPY "Advice" ("Adviser", "Advisor", "IDFilm", "IDTVSerie", "isFilm") FROM stdin;
    public       postgres    false    198   �H       �          0    24764    Cart 
   TABLE DATA               D   COPY "Cart" ("User", "FilmID", "Quantity", "TVSerieID") FROM stdin;
    public       postgres    false    193   0I       �          0    24657    Episode 
   TABLE DATA               @   COPY "Episode" ("Path", "TVSerie", episode, season) FROM stdin;
    public       postgres    false    189   pI       �          0    24832 	   Favourite 
   TABLE DATA               K   COPY "Favourite" ("User", "IDMultimedia", "isFilm", "IDSerie") FROM stdin;
    public       postgres    false    197   �I       �          0    24607    Film 
   TABLE DATA               ~   COPY "Film" ("ID", "Title", "Category", "Year", "Director", "Trailer", "VideoOnDemand", "Plot", "Price", "Image") FROM stdin;
    public       postgres    false    185   %J       �          0    24814 
   Friendship 
   TABLE DATA               ;   COPY "Friendship" ("User1", "User2", accepted) FROM stdin;
    public       postgres    false    196   L       �          0    24670    PaymentMethod 
   TABLE DATA               R   COPY "PaymentMethod" ("CardNumber", "User", "Code", "ExpirationDate") FROM stdin;
    public       postgres    false    190   �L       �          0    24683    RankingFilm 
   TABLE DATA               8   COPY "RankingFilm" ("Rank", "User", "Film") FROM stdin;
    public       postgres    false    191   'M       �          0    24701    RankingTVSerie 
   TABLE DATA               >   COPY "RankingTVSerie" ("Rank", "User", "TVSerie") FROM stdin;
    public       postgres    false    192   cM       �          0    24615    TVSerie 
   TABLE DATA               �   COPY "TVSerie" ("ID", "Title", "Category", "Director", "Year", "Completed", "Seasons", "Trailer", "Plot", "Price", "Image") FROM stdin;
    public       postgres    false    186   �M       �          0    24623    User 
   TABLE DATA               m   COPY "User" ("Password", "E-Mail", "Premium", "Admin", "Date", "FirstName", "LastName", "Image") FROM stdin;
    public       postgres    false    187   �R       K           2606    24795    ActorInFilm ActorInFilm_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY "ActorInFilm"
    ADD CONSTRAINT "ActorInFilm_pkey" PRIMARY KEY ("Actor", "FilmID");
 J   ALTER TABLE ONLY public."ActorInFilm" DROP CONSTRAINT "ActorInFilm_pkey";
       public         postgres    false    194    194    194            M           2606    24808 "   ActorInTVSerie ActorInTVSerie_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY "ActorInTVSerie"
    ADD CONSTRAINT "ActorInTVSerie_pkey" PRIMARY KEY ("TVSerieID", "Actor");
 P   ALTER TABLE ONLY public."ActorInTVSerie" DROP CONSTRAINT "ActorInTVSerie_pkey";
       public         postgres    false    195    195    195            ?           2606    24643    Address Address_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY "Address"
    ADD CONSTRAINT "Address_pkey" PRIMARY KEY ("User", "Street", "ZipCode");
 B   ALTER TABLE ONLY public."Address" DROP CONSTRAINT "Address_pkey";
       public         postgres    false    188    188    188    188            I           2606    24771    Cart Cart_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY "Cart"
    ADD CONSTRAINT "Cart_pkey" PRIMARY KEY ("FilmID", "User", "TVSerieID");
 <   ALTER TABLE ONLY public."Cart" DROP CONSTRAINT "Cart_pkey";
       public         postgres    false    193    193    193    193            Q           2606    24885    Favourite Favourite_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY "Favourite"
    ADD CONSTRAINT "Favourite_pkey" PRIMARY KEY ("User", "IDMultimedia", "IDSerie");
 F   ALTER TABLE ONLY public."Favourite" DROP CONSTRAINT "Favourite_pkey";
       public         postgres    false    197    197    197    197            9           2606    24614    Film Film_pkey 
   CONSTRAINT     K   ALTER TABLE ONLY "Film"
    ADD CONSTRAINT "Film_pkey" PRIMARY KEY ("ID");
 <   ALTER TABLE ONLY public."Film" DROP CONSTRAINT "Film_pkey";
       public         postgres    false    185    185            O           2606    24887    Friendship Friendship_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "Friendship"
    ADD CONSTRAINT "Friendship_pkey" PRIMARY KEY ("User1", "User2");
 H   ALTER TABLE ONLY public."Friendship" DROP CONSTRAINT "Friendship_pkey";
       public         postgres    false    196    196    196            A           2606    24725 
   Episode ID 
   CONSTRAINT     ]   ALTER TABLE ONLY "Episode"
    ADD CONSTRAINT "ID" PRIMARY KEY ("TVSerie", episode, season);
 8   ALTER TABLE ONLY public."Episode" DROP CONSTRAINT "ID";
       public         postgres    false    189    189    189    189            C           2606    24677     PaymentMethod PaymentMethod_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY "PaymentMethod"
    ADD CONSTRAINT "PaymentMethod_pkey" PRIMARY KEY ("CardNumber", "User");
 N   ALTER TABLE ONLY public."PaymentMethod" DROP CONSTRAINT "PaymentMethod_pkey";
       public         postgres    false    190    190    190            E           2606    24690    RankingFilm RankingFilm_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "RankingFilm"
    ADD CONSTRAINT "RankingFilm_pkey" PRIMARY KEY ("User", "Film");
 J   ALTER TABLE ONLY public."RankingFilm" DROP CONSTRAINT "RankingFilm_pkey";
       public         postgres    false    191    191    191            G           2606    24708 "   RankingTVSerie RankingTVSerie_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY "RankingTVSerie"
    ADD CONSTRAINT "RankingTVSerie_pkey" PRIMARY KEY ("User", "TVSerie");
 P   ALTER TABLE ONLY public."RankingTVSerie" DROP CONSTRAINT "RankingTVSerie_pkey";
       public         postgres    false    192    192    192            ;           2606    24622    TVSerie TvSerie_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY "TVSerie"
    ADD CONSTRAINT "TvSerie_pkey" PRIMARY KEY ("ID");
 B   ALTER TABLE ONLY public."TVSerie" DROP CONSTRAINT "TvSerie_pkey";
       public         postgres    false    186    186            =           2606    24743    User User_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY "User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY ("E-Mail");
 <   ALTER TABLE ONLY public."User" DROP CONSTRAINT "User_pkey";
       public         postgres    false    187    187            U           2606    24696    RankingFilm Film    FK CONSTRAINT     g   ALTER TABLE ONLY "RankingFilm"
    ADD CONSTRAINT "Film" FOREIGN KEY ("Film") REFERENCES "Film"("ID");
 >   ALTER TABLE ONLY public."RankingFilm" DROP CONSTRAINT "Film";
       public       postgres    false    185    2105    191            Z           2606    24796    ActorInFilm Film    FK CONSTRAINT     i   ALTER TABLE ONLY "ActorInFilm"
    ADD CONSTRAINT "Film" FOREIGN KEY ("FilmID") REFERENCES "Film"("ID");
 >   ALTER TABLE ONLY public."ActorInFilm" DROP CONSTRAINT "Film";
       public       postgres    false    185    194    2105            \           2606    24822    Friendship Friend1    FK CONSTRAINT     n   ALTER TABLE ONLY "Friendship"
    ADD CONSTRAINT "Friend1" FOREIGN KEY ("User1") REFERENCES "User"("E-Mail");
 @   ALTER TABLE ONLY public."Friendship" DROP CONSTRAINT "Friend1";
       public       postgres    false    196    187    2109            ]           2606    24827    Friendship Friend2    FK CONSTRAINT     n   ALTER TABLE ONLY "Friendship"
    ADD CONSTRAINT "Friend2" FOREIGN KEY ("User2") REFERENCES "User"("E-Mail");
 @   ALTER TABLE ONLY public."Friendship" DROP CONSTRAINT "Friend2";
       public       postgres    false    2109    196    187            S           2606    24665    Episode TVSerie    FK CONSTRAINT     l   ALTER TABLE ONLY "Episode"
    ADD CONSTRAINT "TVSerie" FOREIGN KEY ("TVSerie") REFERENCES "TVSerie"("ID");
 =   ALTER TABLE ONLY public."Episode" DROP CONSTRAINT "TVSerie";
       public       postgres    false    186    2107    189            W           2606    24714    RankingTVSerie TVSerie    FK CONSTRAINT     s   ALTER TABLE ONLY "RankingTVSerie"
    ADD CONSTRAINT "TVSerie" FOREIGN KEY ("TVSerie") REFERENCES "TVSerie"("ID");
 D   ALTER TABLE ONLY public."RankingTVSerie" DROP CONSTRAINT "TVSerie";
       public       postgres    false    2107    192    186            [           2606    24809    ActorInTVSerie TVSerie    FK CONSTRAINT     u   ALTER TABLE ONLY "ActorInTVSerie"
    ADD CONSTRAINT "TVSerie" FOREIGN KEY ("TVSerieID") REFERENCES "TVSerie"("ID");
 D   ALTER TABLE ONLY public."ActorInTVSerie" DROP CONSTRAINT "TVSerie";
       public       postgres    false    186    2107    195            R           2606    24744    Address User    FK CONSTRAINT     g   ALTER TABLE ONLY "Address"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 :   ALTER TABLE ONLY public."Address" DROP CONSTRAINT "User";
       public       postgres    false    187    2109    188            T           2606    24749    PaymentMethod User    FK CONSTRAINT     m   ALTER TABLE ONLY "PaymentMethod"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 @   ALTER TABLE ONLY public."PaymentMethod" DROP CONSTRAINT "User";
       public       postgres    false    190    2109    187            V           2606    24754    RankingFilm User    FK CONSTRAINT     k   ALTER TABLE ONLY "RankingFilm"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 >   ALTER TABLE ONLY public."RankingFilm" DROP CONSTRAINT "User";
       public       postgres    false    2109    187    191            X           2606    24759    RankingTVSerie User    FK CONSTRAINT     n   ALTER TABLE ONLY "RankingTVSerie"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 A   ALTER TABLE ONLY public."RankingTVSerie" DROP CONSTRAINT "User";
       public       postgres    false    187    2109    192            Y           2606    24772 	   Cart User    FK CONSTRAINT     d   ALTER TABLE ONLY "Cart"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 7   ALTER TABLE ONLY public."Cart" DROP CONSTRAINT "User";
       public       postgres    false    193    187    2109            ^           2606    24840    Favourite User    FK CONSTRAINT     i   ALTER TABLE ONLY "Favourite"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 <   ALTER TABLE ONLY public."Favourite" DROP CONSTRAINT "User";
       public       postgres    false    2109    187    197            _           2606    24872    Advice ader    FK CONSTRAINT     g   ALTER TABLE ONLY "Advice"
    ADD CONSTRAINT ader FOREIGN KEY ("Adviser") REFERENCES "User"("E-Mail");
 7   ALTER TABLE ONLY public."Advice" DROP CONSTRAINT ader;
       public       postgres    false    198    187    2109            `           2606    24877    Advice ador    FK CONSTRAINT     g   ALTER TABLE ONLY "Advice"
    ADD CONSTRAINT ador FOREIGN KEY ("Advisor") REFERENCES "User"("E-Mail");
 7   ALTER TABLE ONLY public."Advice" DROP CONSTRAINT ador;
       public       postgres    false    187    198    2109            �      x��NM�+UJM-K-�4����� D�k      �      x������ � �      �   �   x����
�@F��S���S�;�(��js��ǹ2s��P
����}�~��&�-��gհ���<�A+t� -*aL��8/A*7��I:A&$��m�����v�'�����L�Jr���sw����l|Cz����|:<t�Qtf�XB#"/ƚb1z���O�ޱShd68z�u�����8�␼      �   ?   x�K�IL�.H-*�t��/�M����,�L��&;��E��s9�8u9K��H�cH��=1z\\\ ~�7�      �   0   x�K�IL�.H-*�t��/�M����,�4�4��5�J�*i������ �CD      �   _   x�+�LI���sI�M�K�/)�/�H540�7�7�0��-0�4�4�4�*ã��Θ�:�:<��*��6�U�p~u��Vr�^C�=... Y�Q�      �   6   x�K�IL�.H-*�t��/�M����,��5�L�4�J�*i�	R�C�"���� ��L      �   �  x���Oo�0���O1�V�&�-H��P��-������d�6�X���o�I����H�5�3Ͽ�U�U�ӯlW��-e��,�{��ی�M՚�p�L����!9�Ta^�)�T���g���nV�u�S��ɾC�lS�ԙ�Q���ܸ̿W٣'Kq\��@�U�S ���%P���E�T#8�F�MbUIc�R���a��G�x@ j40P��C���QG�0,�J�='�B!�?UW�����L���� �f�}j<sفK~R?{Q6�F�Lv/��+9���"i�W2XcȳU^^������c��_����cl�;V6/��//���M��+��_���=�~�c�/	���9ɝx1l��+�ɉ�35<�Z�!��j	�pv��&�����l����Fn@x���i�a*{˃�G�j�u����ł���(���^%�V¹�U�tk$�"qA�B+��F��$�b!���:/��ӵ^�=��s���b���$�      �   ^   x�K�IL�.H-*�t��/�M����,�L�K)JM�+H,.�/J�4p�/-�����K���L�J£	����+,��'�I�Q%8��aH	W� ��J�      �   �   x�]�1� ��d#섍�t��QH�z�R�Q��Gf2�LH��|X�X�;���9��WA���'iشM�|�ks�(Ў=(���J�j�{�%Zp�US�K��I�E�c̷�Ph����S%�� �_8�      �   ,   x�3�L�IL�.H-*�t��/�M����,�4�2�!c����� ?$~      �   '   x�3�L�IL�.H-*�t��/�M����,�4����� ��	�      �   @  x�uVMo�6=�a���������$������2�fW�)R ����o���nS_��"g޼y�Fכ�z6�WW�/�]�s���c0�c�9�����������/e̯./�yn�q*Sˍ��%-w���ߵ�۟�4d��8���_̑)eC��ɐ��@�Pqao�d=�%3�ٍ�$1䇘�7��� �[J�o̟D�پ��2yJw����Aϐ����������-�YL�+9=����pޘ;Y�w9(�/�4�1�����R�]�BgJ4->��ȹ6�����!���T�gE�Kl�ޑeADƓYx�������Ꞁ�Zύ��\�Q����OW�f��@��xD��9���_�����IJ�-2��'ZO<D��{�x�3r"��ϯ��g�Q�R�
z�h������'Dm�
K!��|~�?�\!d���9}b�2id9����!�?@n4��w\?������P�Gf!�E�[�)񀤢��
�R�B�fi,SZ,��A:�N{�+��s_�_%�	�9"ˁ�+d*����JѻI�kZcgv)����*�^­P�E��U��HR6��9�o
4�O��}��@3��5�"��W,�����U &*%\�����Q�k!C�g��pvH��xFa�1�|���J<UI���F�?�(��b�\��:����O-易�x�(X�<z&q�)IT��{�8
�@�t@+��`b��s�Ǟ̗�R���0�������J�ғ�� 6(K��:(T�>���l�	<#o�}M	�{ 0��A�ɮ� �bڪ�<�"Z���R7:�VFX"�B��=Q`qh��h\(���,�U�w͇�yۨ� �PLE��D@J.ԱI�Ǩ�1)9����"�l�շ�X���Wrq��l/�G�Bgi�'H����{�׬Ak3H�����=^Z�x?E�rU'���Ɗ�.�<a��Stq�>1V�$*z�)=���\}]�����`��xW��H ө`��1ڶD�%u������:��O�8�M\�Y�x�x/ڨ|���)�/R���?���/llug�6�u<u�,%ђ]mk>�}�ۣ�1�:�rXc�2l���0��*�vj��"[����z :�",�n���K�l���^�~~��N���Rm���I�	{�ȷΆӄdy��V68E�����z�?$������Ʀrޚv*��.G��4p����v
��2f�H��70�jU:i*���cX�YfY����j��E[kiV��tU�0�<��D]�P�몈cV��F�,�n/1�Oé�?6
C�ln��۫ӿ��dF.����%�|�gߚ��� 
Aʻ      �   �   x���A� �5ܥ��P��0�܌�V,8d�oo5��11����idg��ѣ�b���Ё�Tu�-dUl��p��ւH�i�����6*��n���7C�:���� �鷝��[s��6*Q���.�ֲ�)�q!�de�2q}��$���c�Yy��'��k5     