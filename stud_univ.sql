--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-04-13 00:21:47 CDT

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
-- TOC entry 210 (class 1259 OID 16617)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    id integer NOT NULL,
    name text NOT NULL,
    age integer NOT NULL,
    address character varying(50),
    email_id character varying(50) NOT NULL,
    dept_id integer NOT NULL
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16639)
-- Name: advisor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.advisor (
    ad_id integer NOT NULL,
    ad_name character varying(50) NOT NULL,
    inst_id integer
);


ALTER TABLE public.advisor OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16656)
-- Name: adv; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.adv
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.adv OWNER TO postgres;

--
-- TOC entry 3624 (class 0 OID 0)
-- Dependencies: 216
-- Name: adv; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.adv OWNED BY public.advisor.ad_id;


--
-- TOC entry 209 (class 1259 OID 16612)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    dept_id integer NOT NULL,
    dept_name character varying(50) NOT NULL,
    building character varying(50) NOT NULL
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16649)
-- Name: dept; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dept
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dept OWNER TO postgres;

--
-- TOC entry 3625 (class 0 OID 0)
-- Dependencies: 213
-- Name: dept; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dept OWNED BY public.department.dept_id;


--
-- TOC entry 211 (class 1259 OID 16629)
-- Name: instructor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.instructor (
    inst_id integer NOT NULL,
    doj date NOT NULL,
    salary integer NOT NULL,
    id integer
);


ALTER TABLE public.instructor OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16653)
-- Name: inst; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inst
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inst OWNER TO postgres;

--
-- TOC entry 3626 (class 0 OID 0)
-- Dependencies: 215
-- Name: inst; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inst OWNED BY public.instructor.inst_id;


--
-- TOC entry 217 (class 1259 OID 16675)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    std_id integer NOT NULL,
    id integer NOT NULL,
    ad_id integer,
    gpa integer
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16690)
-- Name: std; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.std
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.std OWNER TO postgres;

--
-- TOC entry 3627 (class 0 OID 0)
-- Dependencies: 218
-- Name: std; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.std OWNED BY public.student.std_id;


--
-- TOC entry 214 (class 1259 OID 16651)
-- Name: usr; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usr
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usr OWNER TO postgres;

--
-- TOC entry 3628 (class 0 OID 0)
-- Dependencies: 214
-- Name: usr; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usr OWNED BY public."User".id;


--
-- TOC entry 3451 (class 2604 OID 16652)
-- Name: User id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User" ALTER COLUMN id SET DEFAULT nextval('public.usr'::regclass);


--
-- TOC entry 3453 (class 2604 OID 16657)
-- Name: advisor ad_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advisor ALTER COLUMN ad_id SET DEFAULT nextval('public.adv'::regclass);


--
-- TOC entry 3450 (class 2604 OID 16650)
-- Name: department dept_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department ALTER COLUMN dept_id SET DEFAULT nextval('public.dept'::regclass);


--
-- TOC entry 3452 (class 2604 OID 16654)
-- Name: instructor inst_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instructor ALTER COLUMN inst_id SET DEFAULT nextval('public.inst'::regclass);


--
-- TOC entry 3454 (class 2604 OID 16691)
-- Name: student std_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student ALTER COLUMN std_id SET DEFAULT nextval('public.std'::regclass);


--
-- TOC entry 3610 (class 0 OID 16617)
-- Dependencies: 210
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."User" (id, name, age, address, email_id, dept_id) FROM stdin;
1	Sagar	18	2801 South King Drive	Sagar1@hawk.iit.edu	1
2	Amogh	19	2802 South King Drive	Amogh2@hawk.iit.edu	1
3	Hari	20	2803 South King Drive	Hari3@hawk.iit.edu	2
4	Shashank	21	2804 South King Drive	Shashank4@hawk.iit.edu	3
5	Rajesh	21	2805 South King Drive	Rajesh5@hawk.iit.edu	4
6	Aman	22	2806 South King Drive	Aman6@hawk.iit.edu	1
7	Harshitha	23	2807 South King Drive	Harshitha7@hawk.iit.edu	1
8	Yuvraj	24	2808 South King Drive	Yuvraj8@hawk.iit.edu	5
9	Issa	25	2809 South King Drive	Issa9@hawk.iit.edu	6
10	Santosh	26	2810 South King Drive	Santosh10@hawk.iit.edu	7
11	Priyanka	24	2811 South King Drive	Priyanka11@hawk.iit.edu	8
12	Kaushik	21	2812 South King Drive	Kaushik12@hawk.iit.edu	9
13	Amit	25	2813 South King Drive	Amit13@hawk.iit.edu	10
14	Kamal	27	2814 South King Drive	Kamal14@hawk.iit.edu	15
15	Sabin	30	2815 South King Drive	Sabin15@hawk.iit.edu	12
\.


--
-- TOC entry 3612 (class 0 OID 16639)
-- Dependencies: 212
-- Data for Name: advisor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.advisor (ad_id, ad_name, inst_id) FROM stdin;
1	Sagar	1
2	Amogh	2
\.


--
-- TOC entry 3609 (class 0 OID 16612)
-- Dependencies: 209
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (dept_id, dept_name, building) FROM stdin;
1	Computer Science	Herman Hall
2	Architecture	Stuart Building
3	Physics	MTCC
4	Biology	ABC
5	Music	BCD
6	Chemistry	AZX
7	Business	AER
8	Histroy	AFR
9	Food Science	ATU
10	ITM	AHY
11	Data Science	AWE
12	Artificial Intelligence	TER
13	Accounting	ARE
14	English Language Services	ASFG
15	Humanities	SDFG
\.


--
-- TOC entry 3611 (class 0 OID 16629)
-- Dependencies: 211
-- Data for Name: instructor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.instructor (inst_id, doj, salary, id) FROM stdin;
1	1997-06-19	28000	1
2	1995-12-22	35000	2
3	1999-07-14	10547	6
4	1998-10-20	78000	8
\.


--
-- TOC entry 3617 (class 0 OID 16675)
-- Dependencies: 217
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (std_id, id, ad_id, gpa) FROM stdin;
1	3	1	2
2	4	1	2
3	5	2	3
4	7	2	3
5	9	2	3
6	10	2	3
7	11	2	3
8	12	2	3
9	13	2	3
10	14	2	3
11	15	2	3
\.


--
-- TOC entry 3629 (class 0 OID 0)
-- Dependencies: 216
-- Name: adv; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adv', 2, true);


--
-- TOC entry 3630 (class 0 OID 0)
-- Dependencies: 213
-- Name: dept; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dept', 15, true);


--
-- TOC entry 3631 (class 0 OID 0)
-- Dependencies: 215
-- Name: inst; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inst', 4, true);


--
-- TOC entry 3632 (class 0 OID 0)
-- Dependencies: 218
-- Name: std; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.std', 11, true);


--
-- TOC entry 3633 (class 0 OID 0)
-- Dependencies: 214
-- Name: usr; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usr', 15, true);


--
-- TOC entry 3458 (class 2606 OID 16623)
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


--
-- TOC entry 3462 (class 2606 OID 16643)
-- Name: advisor advisor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advisor
    ADD CONSTRAINT advisor_pkey PRIMARY KEY (ad_id);


--
-- TOC entry 3456 (class 2606 OID 16616)
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (dept_id);


--
-- TOC entry 3460 (class 2606 OID 16633)
-- Name: instructor instructor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instructor
    ADD CONSTRAINT instructor_pkey PRIMARY KEY (inst_id);


--
-- TOC entry 3464 (class 2606 OID 16679)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (std_id);


--
-- TOC entry 3469 (class 2606 OID 16685)
-- Name: student fk_adv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_adv FOREIGN KEY (ad_id) REFERENCES public.advisor(ad_id) ON DELETE CASCADE;


--
-- TOC entry 3465 (class 2606 OID 16624)
-- Name: User fk_dept; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT fk_dept FOREIGN KEY (dept_id) REFERENCES public.department(dept_id) ON DELETE CASCADE;


--
-- TOC entry 3467 (class 2606 OID 16644)
-- Name: advisor fk_inst; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advisor
    ADD CONSTRAINT fk_inst FOREIGN KEY (inst_id) REFERENCES public.instructor(inst_id) ON DELETE CASCADE;


--
-- TOC entry 3466 (class 2606 OID 16634)
-- Name: instructor fk_us; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instructor
    ADD CONSTRAINT fk_us FOREIGN KEY (id) REFERENCES public."User"(id) ON DELETE CASCADE;


--
-- TOC entry 3468 (class 2606 OID 16680)
-- Name: student fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_user FOREIGN KEY (id) REFERENCES public."User"(id) ON DELETE CASCADE;


-- Completed on 2022-04-13 00:21:47 CDT

--
-- PostgreSQL database dump complete
--

