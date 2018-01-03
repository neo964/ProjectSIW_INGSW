PGDMP         9                 v         	   PandaFlix    9.6.5    9.6.5 =    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
   Friendship    TABLE     T   CREATE TABLE "Friendship" (
    "User1" text NOT NULL,
    "User2" text NOT NULL
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
    public       postgres    false    194   �E       �          0    24801    ActorInTVSerie 
   TABLE DATA               9   COPY "ActorInTVSerie" ("Actor", "TVSerieID") FROM stdin;
    public       postgres    false    195   �E       �          0    24631    Address 
   TABLE DATA               P   COPY "Address" ("Street", "Country", "ZipCode", "District", "User") FROM stdin;
    public       postgres    false    188   �E       �          0    24764    Cart 
   TABLE DATA               D   COPY "Cart" ("User", "FilmID", "Quantity", "TVSerieID") FROM stdin;
    public       postgres    false    193   �E       �          0    24657    Episode 
   TABLE DATA               @   COPY "Episode" ("Path", "TVSerie", episode, season) FROM stdin;
    public       postgres    false    189   5F       �          0    24832 	   Favourite 
   TABLE DATA               K   COPY "Favourite" ("User", "IDMultimedia", "isFilm", "IDSerie") FROM stdin;
    public       postgres    false    197   �F       �          0    24607    Film 
   TABLE DATA               ~   COPY "Film" ("ID", "Title", "Category", "Year", "Director", "Trailer", "VideoOnDemand", "Plot", "Price", "Image") FROM stdin;
    public       postgres    false    185   �F       �          0    24814 
   Friendship 
   TABLE DATA               1   COPY "Friendship" ("User1", "User2") FROM stdin;
    public       postgres    false    196   tP       �          0    24670    PaymentMethod 
   TABLE DATA               R   COPY "PaymentMethod" ("CardNumber", "User", "Code", "ExpirationDate") FROM stdin;
    public       postgres    false    190   �P       �          0    24683    RankingFilm 
   TABLE DATA               8   COPY "RankingFilm" ("Rank", "User", "Film") FROM stdin;
    public       postgres    false    191   �P       �          0    24701    RankingTVSerie 
   TABLE DATA               >   COPY "RankingTVSerie" ("Rank", "User", "TVSerie") FROM stdin;
    public       postgres    false    192   �P       �          0    24615    TVSerie 
   TABLE DATA               �   COPY "TVSerie" ("ID", "Title", "Category", "Director", "Year", "Completed", "Seasons", "Trailer", "Plot", "Price", "Image") FROM stdin;
    public       postgres    false    186   �P       �          0    24623    User 
   TABLE DATA               m   COPY "User" ("Password", "E-Mail", "Premium", "Admin", "Date", "FirstName", "LastName", "Image") FROM stdin;
    public       postgres    false    187   8V       E           2606    24795    ActorInFilm ActorInFilm_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY "ActorInFilm"
    ADD CONSTRAINT "ActorInFilm_pkey" PRIMARY KEY ("Actor", "FilmID");
 J   ALTER TABLE ONLY public."ActorInFilm" DROP CONSTRAINT "ActorInFilm_pkey";
       public         postgres    false    194    194    194            G           2606    24808 "   ActorInTVSerie ActorInTVSerie_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY "ActorInTVSerie"
    ADD CONSTRAINT "ActorInTVSerie_pkey" PRIMARY KEY ("TVSerieID", "Actor");
 P   ALTER TABLE ONLY public."ActorInTVSerie" DROP CONSTRAINT "ActorInTVSerie_pkey";
       public         postgres    false    195    195    195            9           2606    24643    Address Address_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY "Address"
    ADD CONSTRAINT "Address_pkey" PRIMARY KEY ("User", "Street", "ZipCode");
 B   ALTER TABLE ONLY public."Address" DROP CONSTRAINT "Address_pkey";
       public         postgres    false    188    188    188    188            C           2606    24771    Cart Cart_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY "Cart"
    ADD CONSTRAINT "Cart_pkey" PRIMARY KEY ("FilmID", "User", "TVSerieID");
 <   ALTER TABLE ONLY public."Cart" DROP CONSTRAINT "Cart_pkey";
       public         postgres    false    193    193    193    193            K           2606    24839    Favourite Favourite_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY "Favourite"
    ADD CONSTRAINT "Favourite_pkey" PRIMARY KEY ("User", "IDMultimedia", "IDSerie");
 F   ALTER TABLE ONLY public."Favourite" DROP CONSTRAINT "Favourite_pkey";
       public         postgres    false    197    197    197    197            3           2606    24614    Film Film_pkey 
   CONSTRAINT     K   ALTER TABLE ONLY "Film"
    ADD CONSTRAINT "Film_pkey" PRIMARY KEY ("ID");
 <   ALTER TABLE ONLY public."Film" DROP CONSTRAINT "Film_pkey";
       public         postgres    false    185    185            I           2606    24821    Friendship Friendship_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "Friendship"
    ADD CONSTRAINT "Friendship_pkey" PRIMARY KEY ("User2", "User1");
 H   ALTER TABLE ONLY public."Friendship" DROP CONSTRAINT "Friendship_pkey";
       public         postgres    false    196    196    196            ;           2606    24725 
   Episode ID 
   CONSTRAINT     ]   ALTER TABLE ONLY "Episode"
    ADD CONSTRAINT "ID" PRIMARY KEY ("TVSerie", episode, season);
 8   ALTER TABLE ONLY public."Episode" DROP CONSTRAINT "ID";
       public         postgres    false    189    189    189    189            =           2606    24677     PaymentMethod PaymentMethod_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY "PaymentMethod"
    ADD CONSTRAINT "PaymentMethod_pkey" PRIMARY KEY ("CardNumber", "User");
 N   ALTER TABLE ONLY public."PaymentMethod" DROP CONSTRAINT "PaymentMethod_pkey";
       public         postgres    false    190    190    190            ?           2606    24690    RankingFilm RankingFilm_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "RankingFilm"
    ADD CONSTRAINT "RankingFilm_pkey" PRIMARY KEY ("User", "Film");
 J   ALTER TABLE ONLY public."RankingFilm" DROP CONSTRAINT "RankingFilm_pkey";
       public         postgres    false    191    191    191            A           2606    24708 "   RankingTVSerie RankingTVSerie_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY "RankingTVSerie"
    ADD CONSTRAINT "RankingTVSerie_pkey" PRIMARY KEY ("User", "TVSerie");
 P   ALTER TABLE ONLY public."RankingTVSerie" DROP CONSTRAINT "RankingTVSerie_pkey";
       public         postgres    false    192    192    192            5           2606    24622    TVSerie TvSerie_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY "TVSerie"
    ADD CONSTRAINT "TvSerie_pkey" PRIMARY KEY ("ID");
 B   ALTER TABLE ONLY public."TVSerie" DROP CONSTRAINT "TvSerie_pkey";
       public         postgres    false    186    186            7           2606    24743    User User_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY "User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY ("E-Mail");
 <   ALTER TABLE ONLY public."User" DROP CONSTRAINT "User_pkey";
       public         postgres    false    187    187            O           2606    24696    RankingFilm Film    FK CONSTRAINT     g   ALTER TABLE ONLY "RankingFilm"
    ADD CONSTRAINT "Film" FOREIGN KEY ("Film") REFERENCES "Film"("ID");
 >   ALTER TABLE ONLY public."RankingFilm" DROP CONSTRAINT "Film";
       public       postgres    false    191    2099    185            T           2606    24796    ActorInFilm Film    FK CONSTRAINT     i   ALTER TABLE ONLY "ActorInFilm"
    ADD CONSTRAINT "Film" FOREIGN KEY ("FilmID") REFERENCES "Film"("ID");
 >   ALTER TABLE ONLY public."ActorInFilm" DROP CONSTRAINT "Film";
       public       postgres    false    185    2099    194            V           2606    24822    Friendship Friend1    FK CONSTRAINT     n   ALTER TABLE ONLY "Friendship"
    ADD CONSTRAINT "Friend1" FOREIGN KEY ("User1") REFERENCES "User"("E-Mail");
 @   ALTER TABLE ONLY public."Friendship" DROP CONSTRAINT "Friend1";
       public       postgres    false    187    2103    196            W           2606    24827    Friendship Friend2    FK CONSTRAINT     n   ALTER TABLE ONLY "Friendship"
    ADD CONSTRAINT "Friend2" FOREIGN KEY ("User2") REFERENCES "User"("E-Mail");
 @   ALTER TABLE ONLY public."Friendship" DROP CONSTRAINT "Friend2";
       public       postgres    false    2103    196    187            Y           2606    24845    Favourite IDFilm    FK CONSTRAINT     o   ALTER TABLE ONLY "Favourite"
    ADD CONSTRAINT "IDFilm" FOREIGN KEY ("IDMultimedia") REFERENCES "Film"("ID");
 >   ALTER TABLE ONLY public."Favourite" DROP CONSTRAINT "IDFilm";
       public       postgres    false    2099    197    185            Z           2606    24850    Favourite IDSerie    FK CONSTRAINT     n   ALTER TABLE ONLY "Favourite"
    ADD CONSTRAINT "IDSerie" FOREIGN KEY ("IDSerie") REFERENCES "TVSerie"("ID");
 ?   ALTER TABLE ONLY public."Favourite" DROP CONSTRAINT "IDSerie";
       public       postgres    false    2101    186    197            M           2606    24665    Episode TVSerie    FK CONSTRAINT     l   ALTER TABLE ONLY "Episode"
    ADD CONSTRAINT "TVSerie" FOREIGN KEY ("TVSerie") REFERENCES "TVSerie"("ID");
 =   ALTER TABLE ONLY public."Episode" DROP CONSTRAINT "TVSerie";
       public       postgres    false    189    2101    186            Q           2606    24714    RankingTVSerie TVSerie    FK CONSTRAINT     s   ALTER TABLE ONLY "RankingTVSerie"
    ADD CONSTRAINT "TVSerie" FOREIGN KEY ("TVSerie") REFERENCES "TVSerie"("ID");
 D   ALTER TABLE ONLY public."RankingTVSerie" DROP CONSTRAINT "TVSerie";
       public       postgres    false    192    186    2101            U           2606    24809    ActorInTVSerie TVSerie    FK CONSTRAINT     u   ALTER TABLE ONLY "ActorInTVSerie"
    ADD CONSTRAINT "TVSerie" FOREIGN KEY ("TVSerieID") REFERENCES "TVSerie"("ID");
 D   ALTER TABLE ONLY public."ActorInTVSerie" DROP CONSTRAINT "TVSerie";
       public       postgres    false    195    2101    186            L           2606    24744    Address User    FK CONSTRAINT     g   ALTER TABLE ONLY "Address"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 :   ALTER TABLE ONLY public."Address" DROP CONSTRAINT "User";
       public       postgres    false    187    2103    188            N           2606    24749    PaymentMethod User    FK CONSTRAINT     m   ALTER TABLE ONLY "PaymentMethod"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 @   ALTER TABLE ONLY public."PaymentMethod" DROP CONSTRAINT "User";
       public       postgres    false    190    2103    187            P           2606    24754    RankingFilm User    FK CONSTRAINT     k   ALTER TABLE ONLY "RankingFilm"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 >   ALTER TABLE ONLY public."RankingFilm" DROP CONSTRAINT "User";
       public       postgres    false    191    187    2103            R           2606    24759    RankingTVSerie User    FK CONSTRAINT     n   ALTER TABLE ONLY "RankingTVSerie"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 A   ALTER TABLE ONLY public."RankingTVSerie" DROP CONSTRAINT "User";
       public       postgres    false    2103    187    192            S           2606    24772 	   Cart User    FK CONSTRAINT     d   ALTER TABLE ONLY "Cart"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 7   ALTER TABLE ONLY public."Cart" DROP CONSTRAINT "User";
       public       postgres    false    193    187    2103            X           2606    24840    Favourite User    FK CONSTRAINT     i   ALTER TABLE ONLY "Favourite"
    ADD CONSTRAINT "User" FOREIGN KEY ("User") REFERENCES "User"("E-Mail");
 <   ALTER TABLE ONLY public."Favourite" DROP CONSTRAINT "User";
       public       postgres    false    2103    197    187            �      x��NM�+UJM-K-�4����� D�k      �      x������ � �      �      x������ � �      �   (   x�K�IL�.H-*�t��/�M����,��5�B�=... �D	�      �   _   x�+�LI���sI�M�K�/)�/�H540�7�7�0��-0�4�4�4�*ã��Θ�:�:<��*��6�U�p~u��Vr�^C�=... Y�Q�      �      x������ � �      �   �	  x��Xێ��}^}E?� �%'�/O+ٲd�J�V� C@�$k��!����P��|D�0_�SU$�"	NbX�L�.�N����;���h�RlM��}实����|��ŭ�qo���zo�۪	cں�&�>�p}=��j�<���BwM]I�����/w~���vw�s5���'꬯�׮���5�.�;�'ǫ����="��M2v�)�ܐ���LX˧��)6U�W��K�~@�8��1��.���S�����{2w!�ϑ���eWٶ��w��!�}CC2��v��+2�\j�!zzh"U���X�QJv�h`�<�}�ΛW.�d.��5o��R��s�y86!�����(n�g���K�����w|2�q}Z��X<< ���9Z�:���<�N.�����������yj�q���H9�6�I=0|���H(�q�r�����4a���i踚(�m����䖼w�h�/IR��a�2 ����#�\�u�#���&��6mQxT�m������∿ �VY���jQ�_��5�B��H�*�C�F�Vƃ�Bp�ϯ���֞>}��0�Ą(SV�x����2��T��gi,���._��'-���0(}|f�n���/ u���±�	Ɩ)�2�ճ;��ظ��:�S�:F��1T�m5��W�	���^$OhSf|���Q�v�mM�����_x)gqT-�L%^�f4.Mk����@+�Px!�R�[Z	�\���i�0	>qq	$�� V������i`��Spx�S A�u���Fd�3u��;{B-� ��9�5_�
��!!E�C�x~}	6��	6���rZʚ*Ǎ�$�����+��s;��{taE��M��"�Z&����"�˷ �{�ak^��[p�%�T\lP�3���{q���
�s��ӯ�os*&^پ'�1�RP��U�K�/kZ��TF`X��5'U����.,�`m�P�� �Dۭ�s>�� L`f�ҵ��, -+���]�墢�y�|�s����`�W�P�J�!�%L9�W��+]��R#J>�z��@����^Dܚ���J�#���%�$��r
���
?Mɨu<��3�\ȇ�@"����$;�cCm�A��K��Y��Ш�ɔ܁~�3"�	zQ�R��i-*��`G�&���o�2�w�cҚ'��PU$LD[1�l߰�K�6C*�1;�";�:0Q�d�{mV>��ˁ�@a�9L���S��}����x�ȯ�p�1��i$J����%)ZښY�y�l��c֭��� y�B\5���x*��H4Z侶��	hEH�ru	���(����@�W�I�SG�����4��#��ZT���N<-NR:�[H�s����Ī�Bs��1�آ�e�y�M�FW�y+`i���'牱�&�*r���o��K�����6�E)$X]���i�s���C�3�Tq���M�N���u�&�Sس��6{���!Q5D�8���t
�J$��l(�J���,Y��*m����e�6/�DCKh8�:��=1���wv�v���d���sS��e��ŀh3�ꦙg �|Y�&F�W��"��s:�yX��,^A���e���?�h���v�Q�Ҳ�8�]�L���|�x� ��T&���0S�Oe�h}�f�
i�v��;-�bh�-�����G�q�lji>�FN��r]���p��)�Ԅ�O3�!�7TJuaL%�Z'H0S٬� x�J�_�ܖk�tM����LU^:��Og��`�aC�秝����_��Y�<�}�!�����Y'���Д���[p;7cmjԑ�X0�f*t,�r����@3t��7�1&7� �k�{P)�Rq�*�����'����nP�w��mMz��K85#�;�+q�v!&]�6��h����f�)x��UIW�� ]�Q����:Qqa/� 5RH]����t��>k�x�&+.��1��e� ��5@?���T�*�w���,&<V]����dZ�n|(���IJܶ:�!��ky��7s;����T��<�cs���ɛ�,�|ϯ\����>m����^/r=�n2]��N9�Դ��G
�IǺ��1����l1�<r�ĹY�|A��yr|�Ŋz�:n�y��Ò�_ly6�,�	�Ӽ3蒮eZ؃0��]VT9Ǽg�G�1�C^r^	T-�ۮ��E�W'�0oFB]�i[�Ã�����w�r�^OR船������_��|�	C�ll����%#\X���Zb�9�'�Q���iL�g�>$0��_�W�V�������ٻ���������������?���۽����|r�h���N^�M����~n;����+�6z^d�>��F�#�	���ڕ��ANdy�
vʨ4�U]���&fY��<�,����p�lyJ��RN[2��?����<z�;Nk,֧QGZ���%����t�a2`�w���?�O��a����� NW�<      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   @  x�uVMo�6=�a���������$������2�fW�)R ����o���nS_��"g޼y�Fכ�z6�WW�/�]�s���c0�c�9�����������/e̯./�yn�q*Sˍ��%-w���ߵ�۟�4d��8���_̑)eC��ɐ��@�Pqao�d=�%3�ٍ�$1䇘�7��� �[J�o̟D�پ��2yJw����Aϐ����������-�YL�+9=����pޘ;Y�w9(�/�4�1�����R�]�BgJ4->��ȹ6�����!���T�gE�Kl�ޑeADƓYx�������Ꞁ�Zύ��\�Q����OW�f��@��xD��9���_�����IJ�-2��'ZO<D��{�x�3r"��ϯ��g�Q�R�
z�h������'Dm�
K!��|~�?�\!d���9}b�2id9����!�?@n4��w\?������P�Gf!�E�[�)񀤢��
�R�B�fi,SZ,��A:�N{�+��s_�_%�	�9"ˁ�+d*����JѻI�kZcgv)����*�^­P�E��U��HR6��9�o
4�O��}��@3��5�"��W,�����U &*%\�����Q�k!C�g��pvH��xFa�1�|���J<UI���F�?�(��b�\��:����O-易�x�(X�<z&q�)IT��{�8
�@�t@+��`b��s�Ǟ̗�R���0�������J�ғ�� 6(K��:(T�>���l�	<#o�}M	�{ 0��A�ɮ� �bڪ�<�"Z���R7:�VFX"�B��=Q`qh��h\(���,�U�w͇�yۨ� �PLE��D@J.ԱI�Ǩ�1)9����"�l�շ�X���Wrq��l/�G�Bgi�'H����{�׬Ak3H�����=^Z�x?E�rU'���Ɗ�.�<a��Stq�>1V�$*z�)=���\}]�����`��xW��H ө`��1ڶD�%u������:��O�8�M\�Y�x�x/ڨ|���)�/R���?���/llug�6�u<u�,%ђ]mk>�}�ۣ�1�:�rXc�2l���0��*�vj��"[����z :�",�n���K�l���^�~~��N���Rm���I�	{�ȷΆӄdy��V68E�����z�?$������Ʀrޚv*��.G��4p����v
��2f�H��70�jU:i*���cX�YfY����j��E[kiV��tU�0�<��D]�P�몈cV��F�,�n/1�Oé�?6
C�ln��۫ӿ��dF.����%�|�gߚ��� 
Aʻ      �   Q   x��M,���L�IL�.H-*�t��/�M����,�ACKK3]C]#N_�� ��L������b����"�\��^VA:W� ~ol     