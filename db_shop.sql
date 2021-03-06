PGDMP         4                z            Shop    14.3    14.3     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                        1262    16394    Shop    DATABASE     c   CREATE DATABASE "Shop" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "Shop";
                postgres    false            ?            1259    16491 	   customers    TABLE     ?   CREATE TABLE public.customers (
    id integer NOT NULL,
    name character varying(225),
    "lastName" character varying(225)
);
    DROP TABLE public.customers;
       public         heap    postgres    false            ?            1259    16490    customers_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.customers_id_seq;
       public          postgres    false    210                       0    0    customers_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;
          public          postgres    false    209            ?            1259    16498    products    TABLE     n   CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(225),
    price numeric
);
    DROP TABLE public.products;
       public         heap    postgres    false            ?            1259    16497    products_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    212                       0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
          public          postgres    false    211            ?            1259    16506 	   purchases    TABLE        CREATE TABLE public.purchases (
    "customerId" integer,
    "productName" character varying(225),
    "purchaseDate" date
);
    DROP TABLE public.purchases;
       public         heap    postgres    false            e           2604    16494    customers id    DEFAULT     l   ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);
 ;   ALTER TABLE public.customers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210            f           2604    16501    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            ?          0    16491 	   customers 
   TABLE DATA           9   COPY public.customers (id, name, "lastName") FROM stdin;
    public          postgres    false    210   ?       ?          0    16498    products 
   TABLE DATA           3   COPY public.products (id, name, price) FROM stdin;
    public          postgres    false    212   ?       ?          0    16506 	   purchases 
   TABLE DATA           P   COPY public.purchases ("customerId", "productName", "purchaseDate") FROM stdin;
    public          postgres    false    213   #                  0    0    customers_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.customers_id_seq', 3, true);
          public          postgres    false    209                       0    0    products_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.products_id_seq', 3, true);
          public          postgres    false    211            h           2606    16496    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    210            j           2606    16505    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    212            ?   +   x?3??,K??e\F??%E`?3?t?/?,??@~? v??      ?   5   x?3??MM,?44?3?2????K-J̉/O,I-?4?s&??$r??1z\\\ A*      ?   M   x?3??MM,?4202?50?52?2????K-J̉/O,I-B?2?%e?eș????j??1?] ]8?¦UĈ+F??? ?.?     