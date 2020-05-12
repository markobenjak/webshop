--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: shop; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA shop;


ALTER SCHEMA shop OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: customer; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.customer (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying,
    email character varying
);


ALTER TABLE shop.customer OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

ALTER TABLE shop.customer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME shop.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 5000
    CACHE 1
    CYCLE
);


--
-- Name: webshop_order; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.webshop_order (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    status character varying NOT NULL,
    total_price_hrk double precision,
    total_price_eur double precision
);


ALTER TABLE shop.webshop_order OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

ALTER TABLE shop.webshop_order ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME shop.order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 5000
    CACHE 1
    CYCLE
);


--
-- Name: order_item; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.order_item (
    id integer NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer
);


ALTER TABLE shop.order_item OWNER TO postgres;

--
-- Name: order_item_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

ALTER TABLE shop.order_item ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME shop.order_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 5000
    CACHE 1
    CYCLE
);


--
-- Name: product; Type: TABLE; Schema: shop; Owner: postgres
--

CREATE TABLE shop.product (
    id integer NOT NULL,
    code character varying(10),
    name character varying,
    price_hrk integer DEFAULT 0 NOT NULL,
    description character varying,
    is_available boolean
);


ALTER TABLE shop.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: shop; Owner: postgres
--

ALTER TABLE shop.product ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME shop.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 5000
    CACHE 1
    CYCLE
);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: shop; Owner: postgres
--

COPY shop.customer (id, first_name, last_name, email) FROM stdin;
1	marko	benjak	marko.benjak@gmail.com
2	Petar	Peric	petar.peric@mail.com
3	first	last	mail
4	first	last	mail
\.


--
-- Data for Name: order_item; Type: TABLE DATA; Schema: shop; Owner: postgres
--

COPY shop.order_item (id, order_id, product_id, quantity) FROM stdin;
1	2	1	3
2	11	5	3
3	11	5	3
4	11	5	3
5	11	5	3
6	11	5	3
7	11	1	3
8	11	1	3
9	11	1	3
10	11	1	3
11	11	1	3
12	11	1	3
13	11	1	3
14	14	2	3
15	14	3	2
16	14	4	1
17	1	1	2
18	1	1	2
19	1	1	2
20	25	2	3
21	25	3	4
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: shop; Owner: postgres
--

COPY shop.product (id, code, name, price_hrk, description, is_available) FROM stdin;
1	testcode12	monitor	1000	prozivod	t
2	code1	monitor	1000	monitor	t
3	code1	monitor1	3000	monitor	t
4	code1	monitor4	3000	monitor	t
5	code1	monitor5	3000	monitor	f
\.


--
-- Data for Name: webshop_order; Type: TABLE DATA; Schema: shop; Owner: postgres
--

COPY shop.webshop_order (id, customer_id, status, total_price_hrk, total_price_eur) FROM stdin;
13	11	SUBMITTED	\N	\N
28	2	SUBMITTED	0	0
14	1	SUBMITTED	0	0
16	1	SUBMITTED	0	0
17	1	SUBMITTED	0	0
18	1	SUBMITTED	0	0
19	1	SUBMITTED	0	0
20	1	SUBMITTED	0	0
21	1	SUBMITTED	0	0
22	1	SUBMITTED	0	0
23	1	SUBMITTED	0	0
24	1	SUBMITTED	0	0
29	1	SUBMITTED	0	0
30	1	SUBMITTED	0	0
31	1	SUBMITTED	0	0
32	1	DRAFT	100	100
\.


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.customer_id_seq', 4, true);


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.order_id_seq', 32, true);


--
-- Name: order_item_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.order_item_id_seq', 21, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: shop; Owner: postgres
--

SELECT pg_catalog.setval('shop.product_id_seq', 5, true);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: order_item order_item_pkey; Type: CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.order_item
    ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);


--
-- Name: webshop_order order_pkey; Type: CONSTRAINT; Schema: shop; Owner: postgres
--

ALTER TABLE ONLY shop.webshop_order
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

