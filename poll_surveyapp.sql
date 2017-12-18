-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2017 at 07:29 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `poll_surveyapp`
--
CREATE DATABASE IF NOT EXISTS `poll_surveyapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `poll_surveyapp`;

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `ID` int(10) NOT NULL,
  `answerContent` varchar(1000) NOT NULL,
  `question_id` int(11) NOT NULL,
  `survey_answer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`ID`, `answerContent`, `question_id`, `survey_answer_id`) VALUES
(1, 'Yes', 2, 1),
(2, 'Games|Swimming', 3, 1),
(3, 'Any text', 4, 1),
(4, 'Yes', 2, 2),
(5, 'Swimming|Reading', 3, 2),
(6, 'Just another answer', 4, 2),
(7, 'No', 2, 3),
(8, 'Games', 3, 3),
(9, 'Any text', 4, 3),
(10, 'Yes', 2, 4),
(11, 'Swimming|Reading', 3, 4),
(12, 'Nothing to tell :P', 4, 4),
(13, '21', 5, 5),
(14, 'Cinema', 6, 5),
(15, 'Action/Adventure|Drama|Sci-Fiction', 7, 5),
(16, 'Inception', 8, 5),
(17, '21', 5, 6),
(18, 'Home', 6, 6),
(19, 'Action|Drama|Horror', 7, 6),
(20, 'No country for old men', 8, 6),
(21, '21', 9, 7),
(22, 'PC', 10, 7),
(23, 'Arcade|Sports', 11, 7),
(24, '5', 12, 7);

-- --------------------------------------------------------

--
-- Table structure for table `choice`
--

CREATE TABLE `choice` (
  `ID` int(10) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `choice`
--

INSERT INTO `choice` (`ID`, `content`, `question_id`) VALUES
(3, 'Yes', 2),
(4, 'No', 2),
(5, 'Games', 3),
(6, 'Swimming', 3),
(7, 'Reading', 3),
(8, 'Home', 6),
(9, 'Cinema', 6),
(10, 'Cafe', 6),
(11, 'Action', 7),
(12, 'Action/Adventure', 7),
(13, 'Adventure', 7),
(14, 'Drama', 7),
(15, 'Romance', 7),
(16, 'Horror', 7),
(17, 'Sci-Fiction', 7),
(18, 'PC', 10),
(19, 'Playstation', 10),
(20, 'Xbox', 10),
(21, 'WII', 10),
(22, 'Mobile', 10),
(23, 'Action', 11),
(24, 'Adventure', 11),
(25, 'Action/Adventure', 11),
(26, 'Arcade', 11),
(27, 'Racing', 11),
(28, 'Fight', 11),
(29, 'Horror', 11),
(30, 'Dance', 11),
(31, 'Sports', 11);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `ID` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`ID`, `content`) VALUES
(1, 'Just test'),
(2, 'Another test'),
(3, 'Admin suspended your survey with name: Survey 2'),
(4, 'user with id = 9 report the survey with id = 4'),
(5, 'user with id = 9 report the survey with id = 4'),
(6, 'Admin suspended your survey with name: Film Survey'),
(7, 'Admin unsuspended your survey with name: Film Survey'),
(8, 'Admin suspended your survey with name: Film Survey');

-- --------------------------------------------------------

--
-- Table structure for table `notification_user`
--

CREATE TABLE `notification_user` (
  `ID` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  `seen` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification_user`
--

INSERT INTO `notification_user` (`ID`, `user_id`, `notification_id`, `seen`) VALUES
(1, 6, 1, 1),
(2, 6, 2, 1),
(3, 6, 3, 1),
(4, 8, 4, 1),
(5, 8, 5, 1),
(6, 9, 6, 1),
(7, 9, 7, 1),
(8, 9, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `ID` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `type` varchar(50) NOT NULL,
  `survey_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`ID`, `content`, `type`, `survey_id`) VALUES
(2, 'Graduated?', 'mcq', 4),
(3, 'Your Hobbies ?', 'checkbox', 4),
(4, 'Tell us about your project you made.', 'open', 4),
(5, 'Your age?', 'open', 5),
(6, 'Favorite place to watch movies?', 'mcq', 5),
(7, 'Your favorite genre?', 'checkbox', 5),
(8, 'Favorite film of all time?', 'open', 5),
(9, 'Your age?', 'open', 6),
(10, 'What is the console you use?', 'mcq', 6),
(11, 'What is your favorite types of games?', 'checkbox', 6),
(12, 'What are the average hours you spent a week on gaming?', 'open', 6);

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `ID` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `survey_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`ID`, `content`, `survey_id`, `user_id`) VALUES
(2, 'It contains inappropriate content', 4, 9);

-- --------------------------------------------------------

--
-- Table structure for table `survey`
--

CREATE TABLE `survey` (
  `ID` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `creationDate` varchar(100) NOT NULL,
  `suspended` tinyint(1) NOT NULL DEFAULT '0',
  `creator_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `survey`
--

INSERT INTO `survey` (`ID`, `title`, `creationDate`, `suspended`, `creator_id`) VALUES
(4, 'Survey 2', '2017/12/10 06:13:28', 0, 6),
(5, 'Film Survey', '2017/12/18 08:03:43', 0, 9),
(6, 'Games Survey', '2017/12/18 08:15:32', 0, 6);

-- --------------------------------------------------------

--
-- Table structure for table `survey_answer`
--

CREATE TABLE `survey_answer` (
  `ID` int(100) NOT NULL,
  `ShowInfo` tinyint(1) NOT NULL,
  `survey_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `survey_answer`
--

INSERT INTO `survey_answer` (`ID`, `ShowInfo`, `survey_id`, `user_id`) VALUES
(1, 0, 4, 6),
(2, 0, 4, 6),
(3, 0, 4, 6),
(4, 1, 4, 9),
(5, 0, 5, 6),
(6, 0, 5, 6),
(7, 0, 6, 9);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mobileNumber` varchar(100) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `suspended` tinyint(1) NOT NULL DEFAULT '0',
  `verified` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `firstName`, `lastName`, `mail`, `password`, `mobileNumber`, `admin`, `suspended`, `verified`) VALUES
(6, 'Amr', 'Atef', 'amr.atef96@hotmail.com', 'abc123', '0111333456', 0, 0, 1),
(8, 'Muhamed', 'Ahmed', 'muhamed@gmail.com', '123456', '123456', 1, 0, 1),
(9, 'Manar', 'Ashraf', 'manarashraf711@gmail.com', 'abcabc', '0123456789', 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `verfication_token`
--

CREATE TABLE `verfication_token` (
  `id` int(11) NOT NULL,
  `token` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `verfication_token`
--

INSERT INTO `verfication_token` (`id`, `token`, `user_id`) VALUES
(1, 'manarashraf711@gmail.com_4e8c4ac9-8ce5-44d0-8660-771a3d93fa7a', 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `question_id` (`question_id`),
  ADD KEY `answer_ibfk_1` (`survey_answer_id`);

--
-- Indexes for table `choice`
--
ALTER TABLE `choice`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `mcq_id` (`question_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `notification_user`
--
ALTER TABLE `notification_user`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `notification_user_ibfk_1` (`notification_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `survey_id` (`survey_id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `survey_id` (`survey_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `survey`
--
ALTER TABLE `survey`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `creator_id` (`creator_id`);

--
-- Indexes for table `survey_answer`
--
ALTER TABLE `survey_answer`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `survey_id` (`survey_id`),
  ADD KEY `survey_answer_ibfk_1` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `verfication_token`
--
ALTER TABLE `verfication_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `choice`
--
ALTER TABLE `choice`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `notification_user`
--
ALTER TABLE `notification_user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `survey`
--
ALTER TABLE `survey`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `survey_answer`
--
ALTER TABLE `survey_answer`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `verfication_token`
--
ALTER TABLE `verfication_token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`survey_answer_id`) REFERENCES `survey_answer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_answer_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `choice`
--
ALTER TABLE `choice`
  ADD CONSTRAINT `fk_choice_questionid` FOREIGN KEY (`question_id`) REFERENCES `question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notification_user`
--
ALTER TABLE `notification_user`
  ADD CONSTRAINT `notification_user_ibfk_1` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `notification_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_question_surveyid` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `report_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `survey`
--
ALTER TABLE `survey`
  ADD CONSTRAINT `fk_survey_userid` FOREIGN KEY (`creator_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `survey_answer`
--
ALTER TABLE `survey_answer`
  ADD CONSTRAINT `survey_answer_ibfk_1` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `survey_answer_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `verfication_token`
--
ALTER TABLE `verfication_token`
  ADD CONSTRAINT `fk_token_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
