-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2024 at 04:48 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doanjava2`
--

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `maChucNang` varchar(255) NOT NULL,
  `tenChucNang` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`maChucNang`, `tenChucNang`) VALUES
('QLKH', 'Quản lý khách hàng'),
('QLNV', 'Quản lý nhân viên'),
('QLPN', 'Quản lý phiếu nhập'),
('QLPX', 'Quản lý phiếu xuất'),
('QLSP', 'Quản lý sản phẩm'),
('QLTK', 'Quản lý tài khoản');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `matk` varchar(11) NOT NULL,
  `tendangnhap` varchar(255) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `tenVaiTro` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`matk`, `tendangnhap`, `matkhau`, `tenVaiTro`, `trangthai`) VALUES
('1', 'truong', '$2a$10$XT0XAGiyxET3m8/wh1O55OKPyjuwcMU/6kmM/c3VNtGLxHpVQYHqO', 'administrator', 1),
('2', 'trung', '$2a$10$zJS1bcTfSs7yU6VIvKZbou.37v4cFIDSh0/hcVlvHnINsfzFSUByC', 'administrator', 1),
('3', 'tien', '$2a$10$i5VfhG83bphPHtReOGjfO.o/nXhwOkH8NEXY0oxAfxOJqNmp0J0GW', 'administrator', 1),
('4', 'toan', '$2a$10$qxBtwzF64g2LwmzG.OEPNOKI/b3Fysr36yos1.OdO7S144QewGjH2', 'administrator', 1);

-- --------------------------------------------------------

--
-- Table structure for table `vaitro`
--

CREATE TABLE `vaitro` (
  `maVaiTro` int(11) NOT NULL,
  `tenVaiTro` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vaitro`
--

INSERT INTO `vaitro` (`maVaiTro`, `tenVaiTro`) VALUES
(1, 'administrator'),
(2, 'khachhang');

-- --------------------------------------------------------

--
-- Table structure for table `vaitro_chucnang`
--

CREATE TABLE `vaitro_chucnang` (
  `maVaiTro` int(11) NOT NULL,
  `maChucNang` varchar(255) NOT NULL,
  `them` tinyint(1) NOT NULL,
  `xoa` tinyint(1) NOT NULL,
  `sua` tinyint(1) NOT NULL
) ;

--
-- Dumping data for table `vaitro_chucnang`
--

INSERT INTO `vaitro_chucnang` (`maVaiTro`, `maChucNang`, `them`, `xoa`, `sua`) VALUES
(1, 'QLKH', 1, 1, 1),
(1, 'QLNV', 1, 1, 1),
(1, 'QLPN', 1, 1, 1),
(1, 'QLPX', 1, 1, 1),
(1, 'QLSP', 1, 1, 1),
(1, 'QLTK', 1, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`maChucNang`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`matk`),
  ADD UNIQUE KEY `username` (`tendangnhap`),
  ADD UNIQUE KEY `tendangnhap` (`tendangnhap`);

--
-- Indexes for table `vaitro`
--
ALTER TABLE `vaitro`
  ADD PRIMARY KEY (`maVaiTro`),
  ADD UNIQUE KEY `tenVaiTro` (`tenVaiTro`);

--
-- Indexes for table `vaitro_chucnang`
--
ALTER TABLE `vaitro_chucnang`
  ADD PRIMARY KEY (`maVaiTro`,`maChucNang`);
  ADD CONSTRAINT `CoTacDong` CHECK (`them` = 1 OR `xoa` = 1 OR `sua` = 1);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `vaitro`
--
ALTER TABLE `vaitro`
  MODIFY `maVaiTro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
