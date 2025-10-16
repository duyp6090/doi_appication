--
-- PostgreSQL database dump
--

-- Dumped from database version 18.0 (Debian 18.0-1.pgdg13+3)
-- Dumped by pg_dump version 18.0 (Debian 18.0-1.pgdg13+3)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: tbl_locations; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_locations VALUES (1, NULL, NULL, '2025-10-14 05:01:34.552734', '2025-10-14 05:01:34.552734', 'TP HCM - Q1', 'TP HCM', 'Bß║┐n Ngh├⌐', 106.7009, 10.7769);
INSERT INTO public.tbl_locations VALUES (2, NULL, NULL, '2025-10-14 05:01:34.552734', '2025-10-14 05:01:34.552734', 'H├á Nß╗Öi - Ho├án Kiß║┐m', 'H├á Nß╗Öi', 'H├áng Trß╗æng', 105.8522, 21.0285);
INSERT INTO public.tbl_locations VALUES (3, NULL, NULL, '2025-10-14 05:01:34.552734', '2025-10-14 05:01:34.552734', '─É├á Nß║╡ng - Hß║úi Ch├óu', '─É├á Nß║╡ng', 'Thß║ích Thang', 108.2208, 16.0678);
INSERT INTO public.tbl_locations VALUES (4, NULL, NULL, '2025-10-14 06:04:31.113', '2025-10-14 06:04:31.113', '180 L├╜ Tß╗▒ Trß╗ìng, Ph╞░ß╗¥ng Bß║┐n Th├ánh', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.69567826734344, 10.773410056699738);
INSERT INTO public.tbl_locations VALUES (5, NULL, NULL, '2025-10-14 08:40:47.575', '2025-10-14 08:40:47.575', '180 L├╜ Tß╗▒ Trß╗ìng, Ph╞░ß╗¥ng Bß║┐n Th├ánh', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.69571048157735, 10.773431163529741);
INSERT INTO public.tbl_locations VALUES (6, NULL, NULL, '2025-10-14 08:42:15.114', '2025-10-14 08:42:15.114', '12 Nguyß╗àn Hß╗»u Cß║únh, Ph╞░ß╗¥ng 22', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n B├¼nh Thß║ính', 106.71842539507077, 10.794269769433274);
INSERT INTO public.tbl_locations VALUES (7, NULL, NULL, '2025-10-14 08:47:19.031', '2025-10-14 08:47:19.031', '49 Nguyß╗àn V─ân Cß╗½, Ph╞░ß╗¥ng 1', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 5', 106.68556439507034, 10.75559327546719);
INSERT INTO public.tbl_locations VALUES (8, NULL, NULL, '2025-10-14 08:51:55.241', '2025-10-14 08:51:55.241', '215 L├¬ V─ân Sß╗╣, Ph╞░ß╗¥ng 13', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Ph├║ Nhuß║¡n', 106.6697139950707, 10.793244387284089);
INSERT INTO public.tbl_locations VALUES (9, NULL, NULL, '2025-10-14 08:55:41.108', '2025-10-14 08:55:41.108', '92 Nguyß╗àn V─ân Linh, Ph╞░ß╗¥ng T├ón Phong', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 7', 106.70988113924814, 10.729491079885143);
INSERT INTO public.tbl_locations VALUES (10, NULL, NULL, '2025-10-14 09:00:00.349', '2025-10-14 09:00:00.349', '2 Phß║ím V─ân ─Éß╗ông, Ph╞░ß╗¥ng Linh T├óy', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Thß╗º ─Éß╗⌐c', 106.75185663924967, 10.857297035413298);
INSERT INTO public.tbl_locations VALUES (11, NULL, NULL, '2025-10-14 09:03:25.307', '2025-10-14 09:03:25.307', '56 Tr╞░ß╗¥ng Chinh, Ph╞░ß╗¥ng 15', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n T├ón Ph├║', 106.6361586680847, 10.80435387830921);
INSERT INTO public.tbl_locations VALUES (12, NULL, NULL, '2025-10-14 09:06:34.601', '2025-10-14 09:06:34.601', '28 Nguyß╗àn Oanh, Ph╞░ß╗¥ng 1', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n G├▓ Vß║Ñp', 106.67989961041371, 10.827651586479424);
INSERT INTO public.tbl_locations VALUES (13, NULL, NULL, '2025-10-14 09:14:27.134', '2025-10-14 09:14:27.134', '101 Nguyß╗àn Thß╗ï Minh Khai, Ph╞░ß╗¥ng Phß║ím Ng┼⌐ L├úo', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.68833148157732, 10.772051883953239);
INSERT INTO public.tbl_locations VALUES (14, NULL, NULL, '2025-10-14 09:20:18.219', '2025-10-14 09:20:18.219', '18 Cß╗Öng H├▓a, Ph╞░ß╗¥ng 4', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n T├ón B├¼nh', 106.65685079507081, 10.802773118296814);
INSERT INTO public.tbl_locations VALUES (15, NULL, NULL, '2025-10-14 09:58:11.683', '2025-10-14 09:58:11.683', '12 Phß╗æ H├áng B├ái, Ph╞░ß╗¥ng Tr├áng Tiß╗ün', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n Ho├án Kiß║┐m', 105.8528785952676, 21.02502322809665);
INSERT INTO public.tbl_locations VALUES (16, NULL, NULL, '2025-10-14 10:01:07.528', '2025-10-14 10:01:07.528', '89 L├íng Hß║í, Ph╞░ß╗¥ng L├íng Hß║í', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n ─Éß╗æng ─Éa', 105.81514852410307, 21.016247798736483);
INSERT INTO public.tbl_locations VALUES (17, NULL, NULL, '2025-10-14 10:03:37.893', '2025-10-14 10:03:37.893', '25 Trß║ºn Duy H╞░ng, Ph╞░ß╗¥ng Trung Ho├á', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n Cß║ºu Giß║Ñy', 105.80374350876043, 21.014181040484452);
INSERT INTO public.tbl_locations VALUES (18, NULL, NULL, '2025-10-14 11:04:46.835', '2025-10-14 11:04:46.835', '68 Nguyß╗àn Ch├¡ Thanh, Ph╞░ß╗¥ng L├íng Th╞░ß╗úng', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n ─Éß╗æng ─Éa', 105.80752602595258, 21.019620979048817);
INSERT INTO public.tbl_locations VALUES (19, NULL, NULL, '2025-10-14 11:10:11.626', '2025-10-14 11:10:11.626', '98 L├¬ Duß║⌐n, V─ân Miß║┐u ΓÇô Quß╗æc Tß╗¡ Gi├ím', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n Ho├án Kiß║┐m', 105.8413871799251, 21.026667082864968);
INSERT INTO public.tbl_locations VALUES (20, NULL, NULL, '2025-10-14 11:12:23.364', '2025-10-14 11:12:23.364', '2 T├┤n ─Éß╗⌐c Thß║»ng, Ph╞░ß╗¥ng C├ít Linh', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n ─Éß╗æng ─Éa', 105.83577802595295, 21.03060079202223);
INSERT INTO public.tbl_locations VALUES (21, NULL, NULL, '2025-10-14 11:15:16.365', '2025-10-14 11:15:16.365', '23 Xu├ón Thß╗ºy, Ph╞░ß╗¥ng Dß╗ïch Vß╗ìng Hß║¡u', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n Cß║ºu Giß║Ñy', 105.78880912225407, 21.036512011512745);
INSERT INTO public.tbl_locations VALUES (22, NULL, NULL, '2025-10-14 11:20:33.188', '2025-10-14 11:20:33.188', '145 Tr├¡ch S├ái, Ph╞░ß╗¥ng B╞░ß╗ƒi', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n T├óy Hß╗ô', 105.81330862410397, 21.04970832567733);
INSERT INTO public.tbl_locations VALUES (23, NULL, NULL, '2025-10-14 11:25:32.664', '2025-10-14 11:25:32.664', '15 Phß║ím H├╣ng, Ph╞░ß╗¥ng Mß╗╣ ─É├¼nh', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n Nam Tß╗½ Li├¬m', 105.78020051061034, 21.029237051840507);
INSERT INTO public.tbl_locations VALUES (24, NULL, NULL, '2025-10-14 11:33:57.312', '2025-10-14 11:33:57.312', '29 Ng├╡ 65 Phß║ím Ngß╗ìc Thß║ích, Ph╞░ß╗¥ng Kim Li├¬n', 'Th├ánh phß╗æ H├á Nß╗Öi', 'Quß║¡n ─Éß╗æng ─Éa', 105.83450379526717, 21.00906597142082);
INSERT INTO public.tbl_locations VALUES (25, NULL, NULL, '2025-10-14 12:17:10.901', '2025-10-14 12:17:10.901', '15 Bß║ích ─Éß║▒ng, Ph╞░ß╗¥ng Hß║úi Ch├óu', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.2236608933042, 16.08000200269151);
INSERT INTO public.tbl_locations VALUES (26, NULL, NULL, '2025-10-14 12:20:15.372', '2025-10-14 12:20:15.372', '120 Nguyß╗àn V─ân Linh, Ph╞░ß╗¥ng Thß║íc Gi├ín', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Nam Kh├¬', 108.2130604528245, 16.060566256185542);
INSERT INTO public.tbl_locations VALUES (27, NULL, NULL, '2025-10-14 12:22:53.004', '2025-10-14 12:22:53.004', '98 Nguyß╗àn V─ân Thoß║íi, Ph╞░ß╗¥ng Bß║»c Mß╗╣ Ph├║', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Ng┼⌐ H├ánh S╞ín', 108.24087299515305, 16.054796302855472);
INSERT INTO public.tbl_locations VALUES (28, NULL, NULL, '2025-10-14 12:30:55.337', '2025-10-14 12:30:55.337', '88 Trß║ºn Ph├║', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.22388791049596, 16.069789486530034);
INSERT INTO public.tbl_locations VALUES (29, NULL, NULL, '2025-10-14 12:33:06.825', '2025-10-14 12:33:06.825', '20 L├¬ Duß║⌐n', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.22202713933166, 16.0718803937645);
INSERT INTO public.tbl_locations VALUES (30, NULL, NULL, '2025-10-14 12:36:13.086', '2025-10-14 12:36:13.086', '26 L├╜ Th╞░ß╗¥ng Kiß╗çt, Ph╞░ß╗¥ng Thß║ích Thang', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.21956750864666, 16.08042908400515);
INSERT INTO public.tbl_locations VALUES (31, NULL, NULL, '2025-10-14 12:41:23.473', '2025-10-14 12:41:23.473', '35 Nguyß╗àn Hß╗»u Thß╗ì, Ph╞░ß╗¥ng H├▓a Thuß║¡n Nam', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.2086830798105, 16.055453374695478);
INSERT INTO public.tbl_locations VALUES (32, NULL, NULL, '2025-10-14 12:46:15.438', '2025-10-14 12:46:15.438', '40 Nguyß╗àn Ch├¡ Thanh', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.22102023933161, 16.071250608598096);
INSERT INTO public.tbl_locations VALUES (33, NULL, NULL, '2025-10-14 12:48:22.53', '2025-10-14 12:48:22.53', '50 Bß║ích ─Éß║▒ng', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.22439981049594, 16.07288615885254);
INSERT INTO public.tbl_locations VALUES (34, NULL, NULL, '2025-10-14 12:53:25.092', '2025-10-14 12:53:25.092', '98 Nguyß╗àn V─ân Linh', 'Th├ánh phß╗æ ─É├á Nß║╡ng', 'Quß║¡n Hß║úi Ch├óu', 108.21504089700275, 16.06092355626566);
INSERT INTO public.tbl_locations VALUES (35, NULL, NULL, '2025-10-14 13:13:06.997', '2025-10-14 13:13:06.997', '12 Nguyß╗àn Tr├úi, Ph╞░ß╗¥ng Phß║ím Ng┼⌐ L├úo', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.69279833739921, 10.771363986460637);
INSERT INTO public.tbl_locations VALUES (36, NULL, NULL, '2025-10-14 13:14:53.303', '2025-10-14 13:14:53.303', '25 L├¬ Lß╗úi, Ph╞░ß╗¥ng Bß║┐n Ngh├⌐', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.70111089507053, 10.774867943360775);
INSERT INTO public.tbl_locations VALUES (37, NULL, NULL, '2025-10-14 13:20:24.396', '2025-10-14 13:20:24.396', '21 V├╡ V─ân Tß║ºn, Ph╞░ß╗¥ng 6', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 3', 106.69205391041307, 10.778289197197054);
INSERT INTO public.tbl_locations VALUES (38, NULL, NULL, '2025-10-14 13:24:49.506', '2025-10-14 13:24:49.506', '102 Nguyß╗àn Tri Ph╞░╞íng, Ph╞░ß╗¥ng 7', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 5', 106.66964879507033, 10.754189697530748);
INSERT INTO public.tbl_locations VALUES (39, NULL, NULL, '2025-10-14 13:32:59.435', '2025-10-14 13:32:59.435', '36 Phan X├¡ch Long, Ph╞░ß╗¥ng 2', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n Ph├║ Nhuß║¡n', 106.68661065274213, 10.79938608232365);
INSERT INTO public.tbl_locations VALUES (40, NULL, NULL, '2025-10-14 13:37:10.875', '2025-10-14 13:37:10.875', '74 Nguyß╗àn V─ân Cß╗½, Ph╞░ß╗¥ng Nguyß╗àn C╞░ Trinh', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.68513823739907, 10.757115516408303);
INSERT INTO public.tbl_locations VALUES (41, NULL, NULL, '2025-10-14 13:40:15.285', '2025-10-14 13:40:15.285', '88 Nguyß╗àn Hß╗»u Cß║únh, Ph╞░ß╗¥ng 22', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n B├¼nh Thß║ính', 106.71587403739944, 10.789058416336204);
INSERT INTO public.tbl_locations VALUES (42, NULL, NULL, '2025-10-14 13:43:19.007', '2025-10-14 13:43:19.007', '12 Nguyß╗àn Thß╗ï Thß║¡p, Ph╞░ß╗¥ng T├ón Ph├║', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 7', 106.72096690856324, 10.737651732058405);
INSERT INTO public.tbl_locations VALUES (43, NULL, NULL, '2025-10-14 13:45:40.373', '2025-10-14 13:45:40.373', '60 ─Éiß╗çn Bi├¬n Phß╗º, Ph╞░ß╗¥ng 2', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n B├¼nh Thß║ính', 106.70770333555008, 10.801010309618233);
INSERT INTO public.tbl_locations VALUES (44, NULL, NULL, '2025-10-14 13:48:30.696', '2025-10-14 13:48:30.696', '202 L├¬ Lai, Ph╞░ß╗¥ng Phß║ím Ng┼⌐ L├úo', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.69045639507047, 10.769090966875321);
INSERT INTO public.tbl_locations VALUES (45, NULL, NULL, '2025-10-14 13:53:35.147', '2025-10-14 13:53:35.147', '45 Bß║ích ─Éß║▒ng, Ph╞░ß╗¥ng 2', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n T├ón B├¼nh', 106.6713768508928, 10.81479130186305);
INSERT INTO public.tbl_locations VALUES (46, NULL, NULL, '2025-10-14 13:55:48.555', '2025-10-14 13:55:48.555', '120 Cß╗Öng H├▓a, Ph╞░ß╗¥ng 4', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n T├ón B├¼nh', 106.6580808257559, 10.80157685805793);
INSERT INTO public.tbl_locations VALUES (47, NULL, NULL, '2025-10-14 13:57:30.391', '2025-10-14 13:57:30.391', '20 Phan ─É├¼nh Ph├╣ng, Ph╞░ß╗¥ng 2', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n Ph├║ Nhuß║¡n', 106.68536552390638, 10.79359056991026);
INSERT INTO public.tbl_locations VALUES (48, NULL, NULL, '2025-10-14 13:59:30.57', '2025-10-14 13:59:30.57', '18 V├╡ V─ân Ng├ón, Ph╞░ß╗¥ng Linh Chiß╗âu', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Th├ánh phß╗æ Thß╗º ─Éß╗⌐c', 106.76505376808524, 10.853385564731363);
INSERT INTO public.tbl_locations VALUES (49, NULL, NULL, '2025-10-14 14:01:52.821', '2025-10-14 14:01:52.821', '45 Nguyß╗àn V─ân Qu├í, Ph╞░ß╗¥ng ─É├┤ng H╞░ng Thuß║¡n', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 12', 106.6305629643862, 10.842296972948006);
INSERT INTO public.tbl_locations VALUES (50, NULL, NULL, '2025-10-14 14:05:00.935', '2025-10-14 14:05:00.935', '210 L├╜ Tß╗▒ Trß╗ìng, Ph╞░ß╗¥ng Bß║┐n Th├ánh', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.69502998157738, 10.772960704877967);
INSERT INTO public.tbl_locations VALUES (51, NULL, NULL, '2025-10-14 14:08:16.953', '2025-10-14 14:08:16.953', '61 Nguyß╗àn Hß╗»u Cß║únh, Ph╞░ß╗¥ng 22', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n B├¼nh Thß║ính', 106.71794292390634, 10.7923161271105);
INSERT INTO public.tbl_locations VALUES (52, NULL, NULL, '2025-10-14 14:10:22.602', '2025-10-14 14:10:22.602', '90 Phß║ím Ng┼⌐ L├úo, Ph╞░ß╗¥ng Phß║ím Ng┼⌐ L├úo', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.69374163739917, 10.769302566712232);
INSERT INTO public.tbl_locations VALUES (53, NULL, NULL, '2025-10-14 14:13:19.393', '2025-10-14 14:13:19.393', '45 Tr╞░ß╗¥ng Chinh, Ph╞░ß╗¥ng T├óy Thß║ính', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n T├ón Ph├║', 106.63401102390657, 10.808708197786784);
INSERT INTO public.tbl_locations VALUES (54, NULL, NULL, '2025-10-14 14:17:48.322', '2025-10-14 14:17:48.322', '42 ─É╞░ß╗¥ng Nguyß╗àn V─ân Cß╗½', 'Th├ánh phß╗æ Hß╗ô Ch├¡ Minh', 'Quß║¡n 1', 106.68580216808414, 10.756207599532502);


--
-- Data for Name: tbl_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_user VALUES (1, NULL, NULL, '2025-10-14 05:01:34.539271', '2025-10-14 05:01:34.539271', 'alice', 'alice@example.com', '0901234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '1998-05-12', 'ACTIVE');
INSERT INTO public.tbl_user VALUES (2, NULL, NULL, '2025-10-14 05:01:34.539271', '2025-10-14 05:01:34.539271', 'bob', 'bob@example.com', '0902234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '1995-09-20', 'ACTIVE');
INSERT INTO public.tbl_user VALUES (3, NULL, NULL, '2025-10-14 05:01:34.539271', '2025-10-14 05:01:34.539271', 'carol', 'carol@example.com', '0903234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '2000-02-18', 'INACTIVE');
INSERT INTO public.tbl_user VALUES (4, NULL, NULL, '2025-10-14 05:01:34.539271', '2025-10-14 05:01:34.539271', 'david', 'david@example.com', '0904234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '1992-11-01', 'ACTIVE');
INSERT INTO public.tbl_user VALUES (5, NULL, NULL, '2025-10-14 05:02:56.83', '2025-10-14 05:02:56.83', 'minhkhoi', NULL, NULL, '$2a$10$QbKkQyy/T7egfHdg.qDwSOfqq2InLjvqMF6IbqJp89aRtIotNnd32', NULL, 'ACTIVE');
INSERT INTO public.tbl_user VALUES (6, NULL, NULL, '2025-10-14 09:23:02.985', '2025-10-14 09:23:02.985', 'phamduy', NULL, NULL, '$2a$10$NxhGGqRmNvetDX/pTcxsEuXiyh68AFxW2Ojh8v3ywFQnCxPNc5r6G', NULL, 'ACTIVE');
INSERT INTO public.tbl_user VALUES (7, NULL, NULL, '2025-10-14 12:00:06.217', '2025-10-14 12:00:06.217', 'ngokiet', NULL, NULL, '$2a$10$IejE4J.jQf6eQE.6cpPt.uZqTHXyknpp2NdqKyEa57/LVjOBQnx3.', NULL, 'ACTIVE');
INSERT INTO public.tbl_user VALUES (8, NULL, NULL, '2025-10-14 13:09:34.555', '2025-10-14 13:09:34.555', 'duchuy', NULL, NULL, '$2a$10$vmOK7DiXNj./ATUIljggJOqTtVD0PMuRiO9ghgHQIE4pC/D/BHkha', NULL, 'ACTIVE');
INSERT INTO public.tbl_user VALUES (9, NULL, NULL, '2025-10-14 13:52:07.136', '2025-10-14 13:52:07.136', 'baolong', NULL, NULL, '$2a$10$JU2jrVMIAtTbSq79Uv1cEe3SO1.CmgpIiDp98X/ABele2Pmd1/ygO', NULL, 'ACTIVE');


--
-- Data for Name: tbl_cars; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_cars VALUES (1, NULL, NULL, '2025-10-14 05:01:34.556263', '2025-10-14 05:01:34.556263', 2, 'Toyota', 'Vios', 2020, '51A-12345', 150000.00, 1, NULL);
INSERT INTO public.tbl_cars VALUES (2, NULL, NULL, '2025-10-14 05:01:34.556263', '2025-10-14 05:01:34.556263', 2, 'Honda', 'Civic', 2021, '51B-54321', 200000.00, 1, NULL);
INSERT INTO public.tbl_cars VALUES (3, NULL, NULL, '2025-10-14 05:01:34.556263', '2025-10-14 05:01:34.556263', 4, 'Ford', 'Focus', 2019, '30A-67890', 180000.00, 2, NULL);
INSERT INTO public.tbl_cars VALUES (5, NULL, NULL, '2025-10-14 08:40:47.613', '2025-10-14 08:40:47.613', 5, 'Yamaha', 'Exciter 155 VVA', 2023, '59A3-45219', 80000.00, 5, '{"hnymgwdgitj7jbrgjudd": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431246/hnymgwdgitj7jbrgjudd.jpg", "jf9cmeamzym4x42rkpq6": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431246/jf9cmeamzym4x42rkpq6.jpg", "lzr01hqayxmpvrxxepxf": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431245/lzr01hqayxmpvrxxepxf.jpg", "xtzmlmvfkcgpdtmruy7f": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431245/xtzmlmvfkcgpdtmruy7f.jpg"}');
INSERT INTO public.tbl_cars VALUES (6, NULL, NULL, '2025-10-14 08:42:15.117', '2025-10-14 08:42:15.117', 5, 'Honda', 'Air Blade 160', 2022, '59C1-89234', 70000.00, 6, '{"coys3ci0dsd75hd3pejr": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431330/coys3ci0dsd75hd3pejr.jpg", "ikkyl8jddr6qo7vcksac": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431331/ikkyl8jddr6qo7vcksac.jpg", "jxme0qttqasafx5t38ba": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431330/jxme0qttqasafx5t38ba.jpg", "ta1ymyqgtr9ux75fuih2": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431334/ta1ymyqgtr9ux75fuih2.jpg"}');
INSERT INTO public.tbl_cars VALUES (7, NULL, NULL, '2025-10-14 08:47:19.033', '2025-10-14 08:47:19.033', 5, 'Suzuki', 'Raider R150 Fi', 2023, '59H2-34678', 85000.00, 7, '{"bce0s6fbwdgdzm3rfbnl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431638/bce0s6fbwdgdzm3rfbnl.jpg", "bra2vmcyomxbgmmkkk0i": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431638/bra2vmcyomxbgmmkkk0i.jpg"}');
INSERT INTO public.tbl_cars VALUES (8, NULL, NULL, '2025-10-14 08:51:55.245', '2025-10-14 08:51:55.245', 5, 'Piaggio', 'Vespa Sprint 150 ABS', 2023, '59B2-78123', 100000.00, 8, '{"c1mjv1ingp9ehdwd1iyl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431914/c1mjv1ingp9ehdwd1iyl.jpg", "lrqpwa7o2dk3bdftxagm": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431914/lrqpwa7o2dk3bdftxagm.jpg", "qeawfxobxz3zxljuon1g": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431914/qeawfxobxz3zxljuon1g.jpg", "unfxodzks26lhv93eow9": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760431914/unfxodzks26lhv93eow9.jpg"}');
INSERT INTO public.tbl_cars VALUES (9, NULL, NULL, '2025-10-14 08:55:41.111', '2025-10-14 08:55:41.111', 5, 'Honda', 'CB150R Streetfire', 2023, '59M1-33455', 120000.00, 9, '{"jma8bwmthtywz4c4jgxn": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432139/jma8bwmthtywz4c4jgxn.jpg", "ltdzcsigs6mgjeoszgvs": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432140/ltdzcsigs6mgjeoszgvs.jpg", "mvbbtqocwzhzzgt9keax": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432139/mvbbtqocwzhzzgt9keax.jpg", "qrsa8bwm22n0v92vb91q": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432140/qrsa8bwm22n0v92vb91q.jpg"}');
INSERT INTO public.tbl_cars VALUES (10, NULL, NULL, '2025-10-14 09:00:00.352', '2025-10-14 09:00:00.352', 5, 'Kawasaki', 'Z400 ABS', 2020, '59A2-56789', 180000.00, 10, '{"bpk0iins93wgnn4amnpp": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432397/bpk0iins93wgnn4amnpp.webp", "lw3csgla4qiuusctu0t6": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432397/lw3csgla4qiuusctu0t6.webp", "wzxhyfbczhstqznyy7py": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432399/wzxhyfbczhstqznyy7py.webp", "xccxeerfnizsrcivqmvf": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432398/xccxeerfnizsrcivqmvf.webp"}');
INSERT INTO public.tbl_cars VALUES (11, NULL, NULL, '2025-10-14 09:03:25.31', '2025-10-14 09:03:25.31', 5, 'Yamaha', 'MT-03', 2024, '59V2-77231', 190000.00, 11, '{"bnurk4no282tpo06rgq0": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432601/bnurk4no282tpo06rgq0.png", "g8dcwsolqss7x7v0bzct": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432601/g8dcwsolqss7x7v0bzct.png", "itllpykunxwnlii6tv5p": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432599/itllpykunxwnlii6tv5p.png", "ky1c8wqhys2zdtpxowov": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432599/ky1c8wqhys2zdtpxowov.png"}');
INSERT INTO public.tbl_cars VALUES (12, NULL, NULL, '2025-10-14 09:06:34.605', '2025-10-14 09:06:34.605', 5, 'Honda', 'SH 350i', 2023, '59F1-91823', 150000.00, 12, '{"jumcxmnyqbwdbnjs0fxu": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432793/jumcxmnyqbwdbnjs0fxu.jpg", "lmpbqmdm1xrgatg90jeb": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432793/lmpbqmdm1xrgatg90jeb.jpg", "qtydlv74ldkc6ske5nad": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432793/qtydlv74ldkc6ske5nad.jpg", "x0kem6macmln1wflyqsu": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760432794/x0kem6macmln1wflyqsu.jpg"}');
INSERT INTO public.tbl_cars VALUES (13, NULL, NULL, '2025-10-14 09:14:27.138', '2025-10-14 09:14:27.138', 5, 'BMW', 'G 310 R', 2022, '59E2-63712', 220000.00, 13, '{"ariwpuqbmujtuoyp4ak5": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433266/ariwpuqbmujtuoyp4ak5.jpg", "bdstyq4ykoyb0iypz3sx": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433266/bdstyq4ykoyb0iypz3sx.jpg", "qn9sryzehhkzyy0focjl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433265/qn9sryzehhkzyy0focjl.jpg", "vttziicc1imh12z1gsu2": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433265/vttziicc1imh12z1gsu2.jpg"}');
INSERT INTO public.tbl_cars VALUES (14, NULL, NULL, '2025-10-14 09:20:18.222', '2025-10-14 09:20:18.222', 5, 'Ducati', 'Monster 797', 2018, '59B1-46289', 250000.00, 14, '{"j4assj2gvfx5qera9nug": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433616/j4assj2gvfx5qera9nug.jpg", "kyfwbpzwflsg6twz1ix8": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433611/kyfwbpzwflsg6twz1ix8.jpg", "m4s3q0jwcabgx2mxqa3n": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433617/m4s3q0jwcabgx2mxqa3n.jpg", "z5gbhmjv4b4blzpasv1m": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760433609/z5gbhmjv4b4blzpasv1m.jpg"}');
INSERT INTO public.tbl_cars VALUES (15, NULL, NULL, '2025-10-14 09:58:11.687', '2025-10-14 09:58:11.687', 6, 'Honda', 'Vision 2024', 2024, '29B1-63842', 70000.00, 15, '{"bp99enzo4sc3d3d1mspy": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760435889/bp99enzo4sc3d3d1mspy.jpg", "fme1ii54d7zka5kppez3": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760435890/fme1ii54d7zka5kppez3.jpg", "xfqbydredo042aebnygz": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760435889/xfqbydredo042aebnygz.jpg", "yqklm9y0wn6br8cde2mb": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760435889/yqklm9y0wn6br8cde2mb.jpg"}');
INSERT INTO public.tbl_cars VALUES (16, NULL, NULL, '2025-10-14 10:01:07.532', '2025-10-14 10:01:07.532', 6, 'Yamaha', 'Janus Limited 125', 2023, '29D2-78122', 75000.00, 16, '{"gvkrjijs6fjuz2bpffa4": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436065/gvkrjijs6fjuz2bpffa4.jpg", "jwx3s8ttifcdsijfabss": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436067/jwx3s8ttifcdsijfabss.jpg", "sti5wgh2zjombgqrourh": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436065/sti5wgh2zjombgqrourh.jpg", "wsx5jarq9gvhiqcuauzk": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436067/wsx5jarq9gvhiqcuauzk.jpg"}');
INSERT INTO public.tbl_cars VALUES (17, NULL, NULL, '2025-10-14 10:03:37.895', '2025-10-14 10:03:37.895', 6, 'Honda', 'Air Blade 160', 2023, '29X1-48211', 85000.00, 17, '{"etfiq8hjljc9gnb61dhz": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436217/etfiq8hjljc9gnb61dhz.jpg", "rbpic9nibypcjnctjfmj": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436217/rbpic9nibypcjnctjfmj.jpg", "riax1mfyybwnigxym4hq": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436216/riax1mfyybwnigxym4hq.jpg", "vn9jsrclujpxnxph7bmh": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760436217/vn9jsrclujpxnxph7bmh.jpg"}');
INSERT INTO public.tbl_cars VALUES (18, NULL, NULL, '2025-10-14 11:04:46.841', '2025-10-14 11:04:46.841', 6, 'Vespa', 'Primavera 150 ABS', 2024, '29C1-62109', 110000.00, 18, '{"gsvkaadgkz07yfrhrysl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760439885/gsvkaadgkz07yfrhrysl.jpg", "hpbp0gezufrxcl4lhfc5": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760439885/hpbp0gezufrxcl4lhfc5.jpg", "ieti1hjfpgmal6abdxrs": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760439885/ieti1hjfpgmal6abdxrs.jpg", "utriim1lwfd3mrkb8jzb": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760439886/utriim1lwfd3mrkb8jzb.jpg"}');
INSERT INTO public.tbl_cars VALUES (19, NULL, NULL, '2025-10-14 11:10:11.631', '2025-10-14 11:10:11.631', 6, 'Honda', 'CB300R', 2024, '29H1-99453', 160000.00, 19, '{"gqv29sysokbzdftqkwwz": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440210/gqv29sysokbzdftqkwwz.jpg", "qitvvvox77o7newoqah1": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440209/qitvvvox77o7newoqah1.jpg", "s3oyhcpsop7girvr1w0c": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440210/s3oyhcpsop7girvr1w0c.jpg", "svi0fbwfx2tdijwlawes": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440210/svi0fbwfx2tdijwlawes.jpg"}');
INSERT INTO public.tbl_cars VALUES (20, NULL, NULL, '2025-10-14 11:12:23.368', '2025-10-14 11:12:23.368', 6, 'Kawasaki', 'Z650', 2023, '29A1-44673', 220000.00, 20, '{"axaywphyrh9jkojfjqmx": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440342/axaywphyrh9jkojfjqmx.jpg", "i5zemtqhj9u9urkwgd5k": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440342/i5zemtqhj9u9urkwgd5k.jpg", "li5ilhbtepllbtnlgahf": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440342/li5ilhbtepllbtnlgahf.jpg", "s2t1irtg7wddn0ie2frf": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440342/s2t1irtg7wddn0ie2frf.jpg"}');
INSERT INTO public.tbl_cars VALUES (21, NULL, NULL, '2025-10-14 11:15:16.369', '2025-10-14 11:15:16.369', 6, 'Yamaha', 'MT-15', 2024, '29V2-55291', 130000.00, 21, '{"biwwwwv6qclpmtxdrnly": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440515/biwwwwv6qclpmtxdrnly.webp", "kohdlrmltm43wafqmay6": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440515/kohdlrmltm43wafqmay6.webp", "o4hg2kidmdzghtnlsre2": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440515/o4hg2kidmdzghtnlsre2.webp", "yvi4cu96xfg70kwspks2": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440515/yvi4cu96xfg70kwspks2.webp"}');
INSERT INTO public.tbl_cars VALUES (22, NULL, NULL, '2025-10-14 11:20:33.192', '2025-10-14 11:20:33.192', 6, 'Suzuki', 'GSX-S150', 2023, '29E1-71582', 125000.00, 22, '{"hml1zrue2zluugq4lylp": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440832/hml1zrue2zluugq4lylp.jpg", "mnnmqojchbg9zvlpvavl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760440832/mnnmqojchbg9zvlpvavl.jpg"}');
INSERT INTO public.tbl_cars VALUES (23, NULL, NULL, '2025-10-14 11:25:32.668', '2025-10-14 11:25:32.668', 6, 'Ducati', 'Scrambler Icon 800', 2023, '29B3-99231', 260000.00, 23, '{"hypc56kbsbrs83kburti": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760441132/hypc56kbsbrs83kburti.jpg", "idan7fqyqamv1gs5a0ip": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760441131/idan7fqyqamv1gs5a0ip.png", "odl0vfgcez0fuzdxaykw": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760441131/odl0vfgcez0fuzdxaykw.jpg", "uh2dtfhfndhpmqbz5vxc": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760441131/uh2dtfhfndhpmqbz5vxc.jpg"}');
INSERT INTO public.tbl_cars VALUES (24, NULL, NULL, '2025-10-14 11:33:57.319', '2025-10-14 11:33:57.319', 6, 'Harley-Davidson', 'Iron 883', 2023, '29F1-73426', 300000.00, 24, '{"hxrtdwo8beukg505qaex": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760441634/hxrtdwo8beukg505qaex.jpg", "ock02pwtbnuxbobvrkyx": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760441634/ock02pwtbnuxbobvrkyx.jpg", "xn5d0zkvdqfvyib2a1x6": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760441636/xn5d0zkvdqfvyib2a1x6.jpg"}');
INSERT INTO public.tbl_cars VALUES (25, NULL, NULL, '2025-10-14 12:17:10.909', '2025-10-14 12:17:10.909', 7, 'Honda', 'Vision 2023', 2023, '43B1-62581', 70000.00, 25, '{"bpgfaa4svljpwwxrvjkk": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444228/bpgfaa4svljpwwxrvjkk.jpg", "deel8myttrfjtgu5hgyy": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444228/deel8myttrfjtgu5hgyy.jpg", "t4hotujgzx8wkydfqenm": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444229/t4hotujgzx8wkydfqenm.jpg", "u6ddwr52uob16hbfve8j": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444229/u6ddwr52uob16hbfve8j.jpg"}');
INSERT INTO public.tbl_cars VALUES (26, NULL, NULL, '2025-10-14 12:20:15.375', '2025-10-14 12:20:15.375', 7, 'Yamaha', 'Grande Hybrid 125', 2024, '43C1-92453', 80000.00, 26, '{"gkrp0jywqvdxvponxuxi": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444413/gkrp0jywqvdxvponxuxi.jpg", "iwweteclujw3mn3acn9v": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444413/iwweteclujw3mn3acn9v.jpg", "kmfmfn87iiw95f3fswjz": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444414/kmfmfn87iiw95f3fswjz.jpg", "xtjcp8khduwuyj5jdrbf": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444414/xtjcp8khduwuyj5jdrbf.jpg"}');
INSERT INTO public.tbl_cars VALUES (27, NULL, NULL, '2025-10-14 12:22:53.009', '2025-10-14 12:22:53.009', 7, 'Honda', 'Winner X 150', 2023, '43H1-55219', 85000.00, 27, '{"bzjongjdv6bubtyeb0ly": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444569/bzjongjdv6bubtyeb0ly.jpg", "koqc7qlqof7q160grwvr": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444569/koqc7qlqof7q160grwvr.jpg", "pfe31i4c07tleclgqqk9": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444569/pfe31i4c07tleclgqqk9.jpg", "yugqm0aqtevwrwi9fopl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760444572/yugqm0aqtevwrwi9fopl.jpg"}');
INSERT INTO public.tbl_cars VALUES (28, NULL, NULL, '2025-10-14 12:30:55.342', '2025-10-14 12:30:55.342', 7, 'Piaggio', 'Medley 150 ABS', 2024, '43B2-31564', 100000.00, 28, '{"ngonzlreeoj64txh3c9p": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445053/ngonzlreeoj64txh3c9p.jpg", "pkztgmnxwisxanszq80o": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445054/pkztgmnxwisxanszq80o.jpg", "wuttdxifdqqeblxfdvnt": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445053/wuttdxifdqqeblxfdvnt.jpg", "z0nvzdrbkj9oeeo3mak4": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445053/z0nvzdrbkj9oeeo3mak4.jpg"}');
INSERT INTO public.tbl_cars VALUES (29, NULL, NULL, '2025-10-14 12:33:06.828', '2025-10-14 12:33:06.828', 7, 'Suzuki', 'Raider R150 Fi', 2023, '43E1-88762', 85000.00, 29, '{"iipazrictiiy84ox9wtn": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445185/iipazrictiiy84ox9wtn.jpg", "l3ahawtjcyr5igkz7ode": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445185/l3ahawtjcyr5igkz7ode.jpg", "l5pl00pagzzddgq970xk": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445186/l5pl00pagzzddgq970xk.jpg", "pvbn4xdqnuf3skdfxhsm": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445185/pvbn4xdqnuf3skdfxhsm.jpg"}');
INSERT INTO public.tbl_cars VALUES (30, NULL, NULL, '2025-10-14 12:36:13.09', '2025-10-14 12:36:13.09', 7, 'Yamaha', 'MT-03 ABS', 2024, '43B2-31563', 160000.00, 30, '{"ntuvlt6opwaziu4dmptb": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445372/ntuvlt6opwaziu4dmptb.png", "olb9qeuxm9rcragydl9n": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445371/olb9qeuxm9rcragydl9n.png", "vsut3qtvkzhz1gm7ogol": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445371/vsut3qtvkzhz1gm7ogol.png", "xlvhxq0uwkvngrtdmsha": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445371/xlvhxq0uwkvngrtdmsha.png"}');
INSERT INTO public.tbl_cars VALUES (31, NULL, NULL, '2025-10-14 12:41:23.476', '2025-10-14 12:41:23.476', 7, 'Harley-Davidson', 'Street 750', 2023, '43A2-93751', 300000.00, 31, '{"dnmjcewnkr3l4xh1oejf": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445682/dnmjcewnkr3l4xh1oejf.jpg", "iec1tncnuhdzqelrav2g": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445682/iec1tncnuhdzqelrav2g.jpg", "lhgs2apdet0s88edp8gk": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445682/lhgs2apdet0s88edp8gk.jpg", "rmsuk3vkgre3in92byzz": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445682/rmsuk3vkgre3in92byzz.jpg"}');
INSERT INTO public.tbl_cars VALUES (32, NULL, NULL, '2025-10-14 12:46:15.443', '2025-10-14 12:46:15.443', 7, 'CFMOTO', '300NK ABS', 2024, '43C1-87426', 140000.00, 32, '{"b9lcpgnsovfwgz4eehxk": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445973/b9lcpgnsovfwgz4eehxk.jpg", "ccyomfgecvjnrowgwtoa": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445973/ccyomfgecvjnrowgwtoa.jpg", "ix3ib4edov9kfopqsuu1": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445973/ix3ib4edov9kfopqsuu1.jpg", "j6y1vtytifryahhd9bbw": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760445973/j6y1vtytifryahhd9bbw.jpg"}');
INSERT INTO public.tbl_cars VALUES (33, NULL, NULL, '2025-10-14 12:48:22.533', '2025-10-14 12:48:22.533', 7, 'Triumph', 'Trident 660', 2024, '43B2-66347', 250000.00, 33, '{"fxpfecdczrrzztpznjm2": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446100/fxpfecdczrrzztpznjm2.jpg", "pw3jnbs7mbtum3pjerdw": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446102/pw3jnbs7mbtum3pjerdw.jpg", "xyho9gz7raamzhdr5fsy": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446100/xyho9gz7raamzhdr5fsy.jpg", "zps16viai6wphifuv0ra": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446100/zps16viai6wphifuv0ra.jpg"}');
INSERT INTO public.tbl_cars VALUES (34, NULL, NULL, '2025-10-14 12:53:25.098', '2025-10-14 12:53:25.098', 7, 'Benelli', 'TNT 150i', 2017, '43B3-72964', 95000.00, 34, '{"j6hacalpqxb8ittm6xcy": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446395/j6hacalpqxb8ittm6xcy.jpg", "kc2swl5sx8jdlb8ogtbi": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446395/kc2swl5sx8jdlb8ogtbi.jpg", "ovngqjj8frbjryzxelyr": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446395/ovngqjj8frbjryzxelyr.jpg", "pf5vw42lixpxwqsdnfur": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760446396/pf5vw42lixpxwqsdnfur.jpg"}');
INSERT INTO public.tbl_cars VALUES (35, NULL, NULL, '2025-10-14 13:13:07.001', '2025-10-14 13:13:07.001', 8, 'Honda', 'Vision 110', 2023, '59B2-31268', 70000.00, 35, '{"hevmug787lzrfzto7f8a": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760447579/hevmug787lzrfzto7f8a.jpg", "mo2a6rqmru2hjmz0njag": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760447580/mo2a6rqmru2hjmz0njag.jpg", "otexjdh7x3bgvpugx0lw": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760447583/otexjdh7x3bgvpugx0lw.jpg"}');
INSERT INTO public.tbl_cars VALUES (36, NULL, NULL, '2025-10-14 13:14:53.307', '2025-10-14 13:14:53.307', 8, 'Yamaha', 'Grande Hybrid', 2024, '59C1-65894', 90000.00, 36, '{"drrf4l4nkkcmykwysgbp": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760447691/drrf4l4nkkcmykwysgbp.jpg", "mq7ufforffugf24gjhw1": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760447692/mq7ufforffugf24gjhw1.jpg", "mwnpg9p1jjqdrwnkyj87": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760447692/mwnpg9p1jjqdrwnkyj87.jpg", "zmvnebezkm0eg0irsprd": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760447691/zmvnebezkm0eg0irsprd.jpg"}');
INSERT INTO public.tbl_cars VALUES (37, NULL, NULL, '2025-10-14 13:20:24.4', '2025-10-14 13:20:24.4', 8, 'Piaggio', 'Liberty S 125', 2023, '59D1-48751', 95000.00, 37, '{"b3cmocinphvub30xphfc": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448023/b3cmocinphvub30xphfc.jpg", "iwxnql162lhnngp7pnd2": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448023/iwxnql162lhnngp7pnd2.jpg", "vnvlpi8lxneocodpml7m": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448023/vnvlpi8lxneocodpml7m.jpg", "xvidebzifh3o47ymvaec": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448023/xvidebzifh3o47ymvaec.jpg"}');
INSERT INTO public.tbl_cars VALUES (38, NULL, NULL, '2025-10-14 13:24:49.51', '2025-10-14 13:24:49.51', 8, 'Honda', 'Future 125 FI', 2022, '59N2-31247', 80000.00, 38, '{"bitubg1qa0dlh89iws70": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448288/bitubg1qa0dlh89iws70.jpg", "p01bxf2cjhm4m0ilbxgl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448288/p01bxf2cjhm4m0ilbxgl.jpg", "wxlxptwluwb9axoyb92z": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448288/wxlxptwluwb9axoyb92z.jpg", "zdsm5mst2tcdq40l7qso": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448288/zdsm5mst2tcdq40l7qso.jpg"}');
INSERT INTO public.tbl_cars VALUES (39, NULL, NULL, '2025-10-14 13:32:59.438', '2025-10-14 13:32:59.438', 8, 'Kawasaki', 'Versys-X 300 ABS', 2024, '59H2-35197', 250000.00, 39, '{"fsglnnqxyivgpkmfirru": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448777/fsglnnqxyivgpkmfirru.jpg", "nmglqtmyianc11xppcab": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448777/nmglqtmyianc11xppcab.jpg", "rikhxvkc8wi08vplnqev": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448777/rikhxvkc8wi08vplnqev.jpg", "wzgo79velknauwmbf4uk": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760448778/wzgo79velknauwmbf4uk.jpg"}');
INSERT INTO public.tbl_cars VALUES (40, NULL, NULL, '2025-10-14 13:37:10.878', '2025-10-14 13:37:10.878', 8, 'Honda', 'CB300R Neo Sports Caf├⌐', 2019, '59F2-68472', 180000.00, 40, '{"davjh4nf4w7wp8jsubuz": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449028/davjh4nf4w7wp8jsubuz.jpg", "dxfep6fkharq0b5rmrnn": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449029/dxfep6fkharq0b5rmrnn.jpg", "poge4ylfonqofuddeyxl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449028/poge4ylfonqofuddeyxl.jpg", "t3s8mnlt1drwgyyz1wyh": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449028/t3s8mnlt1drwgyyz1wyh.jpg"}');
INSERT INTO public.tbl_cars VALUES (41, NULL, NULL, '2025-10-14 13:40:15.288', '2025-10-14 13:40:15.288', 8, 'Triumph', 'Speed Twin 900', 2024, '59A3-48217', 340000.00, 41, '{"e05dwancnelyggmpfqml": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449214/e05dwancnelyggmpfqml.jpg", "msjours0sr8ryb6oum2o": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449214/msjours0sr8ryb6oum2o.jpg", "u6ttlza73atpiq1fly72": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449213/u6ttlza73atpiq1fly72.jpg"}');
INSERT INTO public.tbl_cars VALUES (42, NULL, NULL, '2025-10-14 13:43:19.01', '2025-10-14 13:43:19.01', 8, 'Honda', 'Winner X V3', 2022, '59X1-48253', 90000.00, 42, '{"fmdkpvpwobr1tcbbodlq": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449395/fmdkpvpwobr1tcbbodlq.jpg", "qpeqrkgfr0d2y3zmaxky": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449397/qpeqrkgfr0d2y3zmaxky.png", "xodmzph8iirkwkb6c6x2": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449397/xodmzph8iirkwkb6c6x2.jpg", "yfzouc1jbv7m9zeal8hr": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449398/yfzouc1jbv7m9zeal8hr.png"}');
INSERT INTO public.tbl_cars VALUES (43, NULL, NULL, '2025-10-14 13:45:40.376', '2025-10-14 13:45:40.376', 8, 'Suzuki', 'GSX-R150', 2023, '59G1-58974', 95000.00, 43, '{"f9vgg7qgvbhte0la7gce": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449535/f9vgg7qgvbhte0la7gce.jpg", "mr2m43asd5ttor7k510t": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449535/mr2m43asd5ttor7k510t.jpg", "ozyfgkqibsptt19td5pr": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449539/ozyfgkqibsptt19td5pr.jpg", "pvagwkxoobwxxolgcbg4": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449535/pvagwkxoobwxxolgcbg4.jpg"}');
INSERT INTO public.tbl_cars VALUES (44, NULL, NULL, '2025-10-14 13:48:30.7', '2025-10-14 13:48:30.7', 8, 'Kawasaki', 'Z650', 2024, '59A1-67493', 320000.00, 44, '{"b906r2l8qkihu8ro3eym": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449710/b906r2l8qkihu8ro3eym.jpg", "pqytgq5ojxs3e3msgdat": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449709/pqytgq5ojxs3e3msgdat.jpg", "zojugk71a87wkazudmb4": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760449709/zojugk71a87wkazudmb4.jpg"}');
INSERT INTO public.tbl_cars VALUES (45, NULL, NULL, '2025-10-14 13:53:35.149', '2025-10-14 13:53:35.149', 9, 'Yamaha', 'Janus 125', 2024, '59X2-57281', 75000.00, 45, '{"aepm6o6p6oigsdstwpyd": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450013/aepm6o6p6oigsdstwpyd.jpg", "epnn715dkxkurhj8grcc": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450013/epnn715dkxkurhj8grcc.jpg", "wfdyytrzu2w2cqpsdc1m": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450014/wfdyytrzu2w2cqpsdc1m.jpg", "x34jaiyuwvzc3ns93hve": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450014/x34jaiyuwvzc3ns93hve.jpg"}');
INSERT INTO public.tbl_cars VALUES (46, NULL, NULL, '2025-10-14 13:55:48.56', '2025-10-14 13:55:48.56', 9, 'Honda', 'Air Blade 160', 2024, '59K2-83614', 95000.00, 46, '{"ltqyjd9cpgkb4jsluee4": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450142/ltqyjd9cpgkb4jsluee4.jpg", "q4dpqbapamdng4paqvzn": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450142/q4dpqbapamdng4paqvzn.jpg", "ryoi9yyte6r2pepbntzg": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450142/ryoi9yyte6r2pepbntzg.jpg", "wvkud4z6nfci0qlsp61q": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450147/wvkud4z6nfci0qlsp61q.jpg"}');
INSERT INTO public.tbl_cars VALUES (47, NULL, NULL, '2025-10-14 13:57:30.393', '2025-10-14 13:57:30.393', 9, 'SYM', 'Elegant 50', 2022, '59M1-37264', 65000.00, 47, '{"keikfsjk8ylci33dfmvo": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450249/keikfsjk8ylci33dfmvo.jpg", "lsqbypglexq7okfyd4il": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450249/lsqbypglexq7okfyd4il.jpg", "smwji7b1wfhrljay2gkr": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450249/smwji7b1wfhrljay2gkr.jpg"}');
INSERT INTO public.tbl_cars VALUES (48, NULL, NULL, '2025-10-14 13:59:30.574', '2025-10-14 13:59:30.574', 9, 'VinFast', 'Klara S', 2024, '59E3-92456', 85000.00, 48, '{"povozukwynq3elzmltkn": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450369/povozukwynq3elzmltkn.jpg", "pvxvp46rzaugv8f7a5nd": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450369/pvxvp46rzaugv8f7a5nd.jpg", "u6ulbqgl5rnq9o67rywo": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450369/u6ulbqgl5rnq9o67rywo.jpg", "v70tush8t84uck5uvwxl": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450369/v70tush8t84uck5uvwxl.jpg"}');
INSERT INTO public.tbl_cars VALUES (49, NULL, NULL, '2025-10-14 14:01:52.824', '2025-10-14 14:01:52.824', 9, 'Honda', 'Winner X 150', 2024, '59F1-68347', 90000.00, 49, '{"epno4sjnlwdayoh6l2h5": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450510/epno4sjnlwdayoh6l2h5.jpg", "kz4twu5mc31wwgntihzv": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450511/kz4twu5mc31wwgntihzv.jpg", "m5vycole8oh5iy0y02ko": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450510/m5vycole8oh5iy0y02ko.jpg", "teinpte0v8njibvdsmth": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450510/teinpte0v8njibvdsmth.jpg"}');
INSERT INTO public.tbl_cars VALUES (50, NULL, NULL, '2025-10-14 14:05:00.939', '2025-10-14 14:05:00.939', 9, 'Yamaha', 'R3', 2022, '59A5-58219', 390000.00, 50, '{"czjy0goja7mujtxm59eo": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450698/czjy0goja7mujtxm59eo.jpg", "gpkxiiervabvuw0nytv9": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450698/gpkxiiervabvuw0nytv9.jpg", "x72uyw2bdule5f4s4beq": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450698/x72uyw2bdule5f4s4beq.jpg", "ynxs1vaqjpkhfogrn8et": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450700/ynxs1vaqjpkhfogrn8et.jpg"}');
INSERT INTO public.tbl_cars VALUES (51, NULL, NULL, '2025-10-14 14:08:16.956', '2025-10-14 14:08:16.956', 9, 'KTM', 'Duke 390', 2020, '59C3-64128', 320000.00, 51, '{"d1qpwjs0wkdngk7iiufd": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450887/d1qpwjs0wkdngk7iiufd.png", "d1rfebao2udinqdnhgig": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450888/d1rfebao2udinqdnhgig.png", "ksnsi3e86rfvp96k9b1k": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450887/ksnsi3e86rfvp96k9b1k.png", "qd3zyoe07nvywpz3cuca": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760450889/qd3zyoe07nvywpz3cuca.png"}');
INSERT INTO public.tbl_cars VALUES (52, NULL, NULL, '2025-10-14 14:10:22.607', '2025-10-14 14:10:22.607', 9, 'Royal Enfield', 'Meteor 350', 2023, '59A7-83651', 240000.00, 52, '{"eundsypauzoxislgquuy": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451021/eundsypauzoxislgquuy.jpg", "ihv6zv83ufpqnnjdcrid": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451021/ihv6zv83ufpqnnjdcrid.jpg", "tdq7raydpiu7bmcmxstu": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451021/tdq7raydpiu7bmcmxstu.jpg", "xdepg5rglww0nhlenrot": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451021/xdepg5rglww0nhlenrot.jpg"}');
INSERT INTO public.tbl_cars VALUES (53, NULL, NULL, '2025-10-14 14:13:19.398', '2025-10-14 14:13:19.398', 9, 'Honda', 'CB500X', 2023, '59C1-63285', 270000.00, 53, '{"a7maempl5oiu4x5xfuug": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451198/a7maempl5oiu4x5xfuug.jpg", "dsgzf6f7bupos23qn0ak": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451198/dsgzf6f7bupos23qn0ak.jpg", "i8cso95elkn5mlffpbrq": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451198/i8cso95elkn5mlffpbrq.jpg", "z8gintzplkbdlirlxxuu": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451198/z8gintzplkbdlirlxxuu.jpg"}');
INSERT INTO public.tbl_cars VALUES (54, NULL, NULL, '2025-10-14 14:17:48.326', '2025-10-14 14:17:48.326', 9, 'Honda', 'Wave Alpha 110', 2023, '59X1-11234', 35000.00, 54, '{"fuaazpc4anz1sykao41b": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451466/fuaazpc4anz1sykao41b.png", "h715nj4i0azokocutbkc": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451467/h715nj4i0azokocutbkc.png", "hnardy8pvdtvar0uin8z": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451466/hnardy8pvdtvar0uin8z.jpg", "vppczfpghlz3ws9jwedu": "https://res.cloudinary.com/dtjub1t7k/image/upload/v1760451465/vppczfpghlz3ws9jwedu.png"}');


--
-- Data for Name: tbl_bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_bookings VALUES (1, NULL, NULL, '2025-10-14 05:01:34.559957', '2025-10-14 05:01:34.559957', 3, 1, '2025-10-01 08:00:00', '2025-10-01 12:00:00', 600000.00, 'COMPLETED');
INSERT INTO public.tbl_bookings VALUES (2, NULL, NULL, '2025-10-14 05:01:34.559957', '2025-10-14 05:01:34.559957', 4, 2, '2025-10-02 09:00:00', '2025-10-02 13:00:00', 800000.00, 'PENDING');


--
-- Data for Name: tbl_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_role VALUES (1, NULL, NULL, '2025-10-14 05:01:34.541698', '2025-10-14 05:01:34.541698', 'ADMIN');
INSERT INTO public.tbl_role VALUES (2, NULL, NULL, '2025-10-14 05:01:34.541698', '2025-10-14 05:01:34.541698', 'OWNER');
INSERT INTO public.tbl_role VALUES (3, NULL, NULL, '2025-10-14 05:01:34.541698', '2025-10-14 05:01:34.541698', 'CUSTOMER');


--
-- Data for Name: tbl_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_group VALUES (1, 1, 1, '2025-10-14 05:01:34.544601', '2025-10-14 05:01:34.544601', 'Admins Group', 'Group for administrators', 1);
INSERT INTO public.tbl_group VALUES (2, 1, 1, '2025-10-14 05:01:34.544601', '2025-10-14 05:01:34.544601', 'Users Group', 'Group for regular users', 2);
INSERT INTO public.tbl_group VALUES (3, 1, 1, '2025-10-14 05:01:34.544601', '2025-10-14 05:01:34.544601', 'Owners Group', 'Group for owners', 3);


--
-- Data for Name: tbl_otp; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: tbl_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_permission VALUES (1, NULL, NULL, '2025-10-14 05:01:34.543042', '2025-10-14 05:01:34.543042', 'read');
INSERT INTO public.tbl_permission VALUES (2, NULL, NULL, '2025-10-14 05:01:34.543042', '2025-10-14 05:01:34.543042', 'create');
INSERT INTO public.tbl_permission VALUES (3, NULL, NULL, '2025-10-14 05:01:34.543042', '2025-10-14 05:01:34.543042', 'update');
INSERT INTO public.tbl_permission VALUES (4, NULL, NULL, '2025-10-14 05:01:34.543042', '2025-10-14 05:01:34.543042', 'delete');
INSERT INTO public.tbl_permission VALUES (5, NULL, NULL, '2025-10-14 05:01:34.543042', '2025-10-14 05:01:34.543042', 'manage');


--
-- Data for Name: tbl_reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_reviews VALUES (1, NULL, NULL, '2025-10-14 05:01:34.563826', '2025-10-14 05:01:34.563826', 1, 5, 'Xe sß║ích, chß╗º xe th├ón thiß╗çn!');
INSERT INTO public.tbl_reviews VALUES (2, NULL, NULL, '2025-10-14 05:01:34.563826', '2025-10-14 05:01:34.563826', 2, 4, 'ß╗ön, nh╞░ng h╞íi trß╗à giß╗¥ nhß║¡n xe.');


--
-- Data for Name: tbl_role_has_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_role_has_permission VALUES (1, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 1, 1);
INSERT INTO public.tbl_role_has_permission VALUES (2, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 1, 2);
INSERT INTO public.tbl_role_has_permission VALUES (3, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 1, 3);
INSERT INTO public.tbl_role_has_permission VALUES (4, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 1, 4);
INSERT INTO public.tbl_role_has_permission VALUES (5, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 1, 5);
INSERT INTO public.tbl_role_has_permission VALUES (6, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 3, 1);
INSERT INTO public.tbl_role_has_permission VALUES (7, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 3, 2);
INSERT INTO public.tbl_role_has_permission VALUES (8, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 3, 3);
INSERT INTO public.tbl_role_has_permission VALUES (9, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 2, 1);
INSERT INTO public.tbl_role_has_permission VALUES (10, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 2, 2);
INSERT INTO public.tbl_role_has_permission VALUES (11, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 2, 3);
INSERT INTO public.tbl_role_has_permission VALUES (12, 1, 1, '2025-10-14 05:01:34.546983', '2025-10-14 05:01:34.546983', 2, 4);


--
-- Data for Name: tbl_token; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: tbl_user_has_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_user_has_group VALUES (1, 1, 1, '2025-10-14 05:01:34.54851', '2025-10-14 05:01:34.54851', 1, 1);
INSERT INTO public.tbl_user_has_group VALUES (2, 1, 1, '2025-10-14 05:01:34.54851', '2025-10-14 05:01:34.54851', 2, 2);
INSERT INTO public.tbl_user_has_group VALUES (3, 1, 1, '2025-10-14 05:01:34.54851', '2025-10-14 05:01:34.54851', 3, 3);
INSERT INTO public.tbl_user_has_group VALUES (4, 1, 1, '2025-10-14 05:01:34.54851', '2025-10-14 05:01:34.54851', 4, 2);


--
-- Data for Name: tbl_user_has_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tbl_user_has_role VALUES (1, 1, 1, '2025-10-14 05:01:34.550723', '2025-10-14 05:01:34.550723', 1, 1);
INSERT INTO public.tbl_user_has_role VALUES (2, 1, 1, '2025-10-14 05:01:34.550723', '2025-10-14 05:01:34.550723', 2, 2);
INSERT INTO public.tbl_user_has_role VALUES (3, 1, 1, '2025-10-14 05:01:34.550723', '2025-10-14 05:01:34.550723', 3, 3);
INSERT INTO public.tbl_user_has_role VALUES (4, 1, 1, '2025-10-14 05:01:34.550723', '2025-10-14 05:01:34.550723', 4, 2);
INSERT INTO public.tbl_user_has_role VALUES (5, NULL, NULL, '2025-10-14 05:02:56.911', '2025-10-14 05:02:56.911', 5, 3);
INSERT INTO public.tbl_user_has_role VALUES (6, NULL, NULL, '2025-10-14 05:03:15.322', '2025-10-14 05:03:15.322', 5, 2);
INSERT INTO public.tbl_user_has_role VALUES (7, NULL, NULL, '2025-10-14 09:23:03.016', '2025-10-14 09:23:03.016', 6, 3);
INSERT INTO public.tbl_user_has_role VALUES (8, NULL, NULL, '2025-10-14 09:23:32.699', '2025-10-14 09:23:32.699', 6, 2);
INSERT INTO public.tbl_user_has_role VALUES (9, NULL, NULL, '2025-10-14 12:00:06.237', '2025-10-14 12:00:06.237', 7, 3);
INSERT INTO public.tbl_user_has_role VALUES (10, NULL, NULL, '2025-10-14 12:00:28.36', '2025-10-14 12:00:28.36', 7, 2);
INSERT INTO public.tbl_user_has_role VALUES (11, NULL, NULL, '2025-10-14 13:09:34.576', '2025-10-14 13:09:34.576', 8, 3);
INSERT INTO public.tbl_user_has_role VALUES (12, NULL, NULL, '2025-10-14 13:09:53.023', '2025-10-14 13:09:53.023', 8, 2);
INSERT INTO public.tbl_user_has_role VALUES (13, NULL, NULL, '2025-10-14 13:52:07.149', '2025-10-14 13:52:07.149', 9, 3);
INSERT INTO public.tbl_user_has_role VALUES (14, NULL, NULL, '2025-10-14 13:52:26.478', '2025-10-14 13:52:26.478', 9, 2);


--
-- Name: tbl_bookings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_bookings_id_seq', 2, true);


--
-- Name: tbl_cars_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_cars_id_seq', 54, true);


--
-- Name: tbl_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_group_id_seq', 3, true);


--
-- Name: tbl_locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_locations_id_seq', 54, true);


--
-- Name: tbl_otp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_otp_id_seq', 1, false);


--
-- Name: tbl_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_permission_id_seq', 5, true);


--
-- Name: tbl_reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_reviews_id_seq', 2, true);


--
-- Name: tbl_role_has_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_role_has_permission_id_seq', 12, true);


--
-- Name: tbl_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_role_id_seq', 3, true);


--
-- Name: tbl_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_token_id_seq', 1, false);


--
-- Name: tbl_user_has_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_user_has_group_id_seq', 4, true);


--
-- Name: tbl_user_has_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_user_has_role_id_seq', 14, true);


--
-- Name: tbl_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tbl_user_id_seq', 9, true);


--
-- PostgreSQL database dump complete
--

\unrestrict iaMUKxfIf7YyOXeA93icK3mfb5JR0J3EgLhhcs6Z2AwVcLR37THLdkxOliypR7v

