--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: denuncia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.denuncia (
    den_id integer NOT NULL,
    den_titulo character varying(40),
    den_texto text,
    den_urgencia numeric(1,0),
    org_id integer,
    den_data timestamp without time zone,
    tip_id integer,
    usu_id integer
);


ALTER TABLE public.denuncia OWNER TO postgres;

--
-- Name: denuncia_den_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.denuncia_den_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.denuncia_den_id_seq OWNER TO postgres;

--
-- Name: denuncia_den_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.denuncia_den_id_seq OWNED BY public.denuncia.den_id;


--
-- Name: feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feedback (
    fee_id integer NOT NULL,
    fee_texto text,
    den_id integer
);


ALTER TABLE public.feedback OWNER TO postgres;

--
-- Name: feedback_fee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.feedback_fee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.feedback_fee_id_seq OWNER TO postgres;

--
-- Name: feedback_fee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.feedback_fee_id_seq OWNED BY public.feedback.fee_id;


--
-- Name: orgaos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orgaos (
    org_id integer NOT NULL,
    org_nome character varying(30)
);


ALTER TABLE public.orgaos OWNER TO postgres;

--
-- Name: orgaos_org_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orgaos_org_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orgaos_org_id_seq OWNER TO postgres;

--
-- Name: orgaos_org_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orgaos_org_id_seq OWNED BY public.orgaos.org_id;


--
-- Name: tipo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo (
    tip_id integer NOT NULL,
    tip_nome character varying(30)
);


ALTER TABLE public.tipo OWNER TO postgres;

--
-- Name: tipo_tip_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_tip_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_tip_id_seq OWNER TO postgres;

--
-- Name: tipo_tip_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_tip_id_seq OWNED BY public.tipo.tip_id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    usu_id integer NOT NULL,
    usu_cpf numeric(11,0) NOT NULL,
    usu_email character varying(40) NOT NULL,
    usu_senha numeric(6,0) NOT NULL,
    usu_nivel numeric(1,0) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_usu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_usu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_usu_id_seq OWNER TO postgres;

--
-- Name: usuario_usu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_usu_id_seq OWNED BY public.usuario.usu_id;


--
-- Name: denuncia den_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.denuncia ALTER COLUMN den_id SET DEFAULT nextval('public.denuncia_den_id_seq'::regclass);


--
-- Name: feedback fee_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback ALTER COLUMN fee_id SET DEFAULT nextval('public.feedback_fee_id_seq'::regclass);


--
-- Name: orgaos org_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orgaos ALTER COLUMN org_id SET DEFAULT nextval('public.orgaos_org_id_seq'::regclass);


--
-- Name: tipo tip_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo ALTER COLUMN tip_id SET DEFAULT nextval('public.tipo_tip_id_seq'::regclass);


--
-- Name: usuario usu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN usu_id SET DEFAULT nextval('public.usuario_usu_id_seq'::regclass);


--
-- Data for Name: denuncia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.denuncia VALUES (1, 'semaforo quebrado', 'semaforo da av da saudede perto do campua I da Unoeste não está funcionando', 4, 4, '2023-05-12 00:00:00', 1, 2);


--
-- Data for Name: feedback; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: orgaos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orgaos VALUES (1, 'SEDUC');
INSERT INTO public.orgaos VALUES (2, 'Policia militar');
INSERT INTO public.orgaos VALUES (3, 'Policia Civil');
INSERT INTO public.orgaos VALUES (4, 'SETRAN');


--
-- Data for Name: tipo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo VALUES (1, 'trânsito');
INSERT INTO public.tipo VALUES (2, 'educação');
INSERT INTO public.tipo VALUES (3, 'ambiental');
INSERT INTO public.tipo VALUES (4, 'segurança');


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario VALUES (1, 12111158963, 'admin@pm.br', 123321, 1);
INSERT INTO public.usuario VALUES (2, 5488889915, 'ze@cidadao.com.br', 123, 2);


--
-- Name: denuncia_den_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.denuncia_den_id_seq', 1, true);


--
-- Name: feedback_fee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.feedback_fee_id_seq', 1, false);


--
-- Name: orgaos_org_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orgaos_org_id_seq', 4, true);


--
-- Name: tipo_tip_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_tip_id_seq', 4, true);


--
-- Name: usuario_usu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_usu_id_seq', 2, true);


--
-- Name: denuncia denuncia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.denuncia
    ADD CONSTRAINT denuncia_pkey PRIMARY KEY (den_id);


--
-- Name: feedback feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (fee_id);


--
-- Name: orgaos orgaos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orgaos
    ADD CONSTRAINT orgaos_pkey PRIMARY KEY (org_id);


--
-- Name: tipo tipo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo
    ADD CONSTRAINT tipo_pkey PRIMARY KEY (tip_id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usu_id);


--
-- Name: denuncia denuncia_org_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.denuncia
    ADD CONSTRAINT denuncia_org_id_fkey FOREIGN KEY (org_id) REFERENCES public.orgaos(org_id) NOT VALID;


--
-- Name: denuncia denuncia_tip_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.denuncia
    ADD CONSTRAINT denuncia_tip_id_fkey FOREIGN KEY (tip_id) REFERENCES public.tipo(tip_id) NOT VALID;


--
-- Name: denuncia denuncia_usu_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.denuncia
    ADD CONSTRAINT denuncia_usu_id_fkey FOREIGN KEY (usu_id) REFERENCES public.usuario(usu_id) NOT VALID;


--
-- Name: feedback feedback_den_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_den_id_fkey FOREIGN KEY (den_id) REFERENCES public.denuncia(den_id);


--
-- PostgreSQL database dump complete
--

