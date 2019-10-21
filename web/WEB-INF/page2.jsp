

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="booking project from Epam-training">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Booking</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header_inner">
            <div class="header_logo">Booking24.by</div>

            <nav class="nav">
                <a class="nav_link" href="#Home">Home</a>
                <a class="nav_link" href="#Country">Country</a>
                <a class="nav_link" href="#Hotels">Hotels</a>
                <a class="nav_link" href="Contact">Contact</a>
                <a class="nav_link" href="register.html">Register</a>
                <a class="nav_link" href="Contact">Sign in</a>
            </nav>
        </div>
    </div>
</header>

<div class="intro">
    <div class="container">
        <h1 class="intro_title">Page2</h1>
        <jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
        <form name="Simple" action="timeaction" method="POST">
            <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
            <input type="submit" name="button" value="Посчитать время"/>
        </form>
        <h2 class="intro_undertitle">Welcome to booking24.by</h2>

        <form class="form1" action="formdata" method="post" name="form1">
            <div class="block_for_search">
                <div class="block">
                    <label for="text">city or hotel</label>
                    <input type="text" id="text" name="text">
                </div>
                <div class="block">
                    <label for="dateIn">check in</label>
                    <input type="date" id="dateIn" name="date">
                </div>
                <div class="block">
                    <label for="dateOut">check out</label>
                    <input type="date" id="dateOut" name="date">
                </div>
                <div class="block">
                    <label for="adults">adults</label>
                    <select name="adults" id="adults">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">other</option>
                    </select>
                </div>
                <div class="block">
                    <label for="children">children</label>
                    <select name="children" id="children">
                        <option value="1">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">other</option>
                    </select>
                </div>
                <div class="block">
                    <label for="submit"></label>
                    <input type="submit" value="search" id="submit">
                </div>
            </div>
        </form>

    </div>
</div>

<section class="section">
    <div class="container">

        <div class="section__header">
            <h2 class="section__title">
                hotels
            </h2>
            <div class="section__text">
                <p>show all hotels</p>
            </div>
        </div>

        <div class="hotels">
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/renaissanceMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="renaissanceMinsk">renaissance</a>
                </div>
            </div>
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/victoriaMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="victoriaMinsk">victoria</a>
                </div>
            </div>
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/planetaMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="planeta">planeta</a>
                </div>
            </div>
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/orbitaMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="orbita">orbita</a>
                </div>
            </div>
        </div>
        <div class="hotels">
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/renaissanceMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="renaissanceMinsk">renaissance</a>
                </div>
            </div>
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/victoriaMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="victoriaMinsk">victoria</a>
                </div>
            </div>
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/planetaMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="planeta">planeta</a>
                </div>
            </div>
            <div class="hotel_item">
                <div class="hotel_image">
                    <img src="img/orbitaMinsk.jpeg" width="100%" alt="">
                    <a class="hot_link" href="orbita">orbita</a>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="contact">
    <div class="title__div">
        <h1 class="contact_title">How to contact us</h1>
    </div>


    <div class="section__header">
        <form id="contactForm" action="" method="post">
            <div class="block">

                <input type="text" required class="form-control" id="user_name" name="user_name" placeholder="Name"
                       style="width: 140px; margin: 0 37px 20px 0;">
                <input type="email" required class="form-control" id="user_mail" name="user_mail"
                       placeholder="Email address" style="width: 240px; margin-bottom: 20px;">

            </div>
            <div class="block" style="margin-bottom: 20px;">
                <textarea class="form-control" name="message" required placeholder="How can we help you?" rows="5"
                          style="width: 420px;"></textarea>

                <input name="source" type="hidden" value="contactForm">
                <input type="hidden" id="token" name="token" value="vKh7rEhizj3AJ/O3lnRv+zP9FVBPVJPgx8iQEY5o/jY=">
            </div>
            <div class="block">
                <input type="submit" id="contactFormSubmit" class="btn" value="Send">
            </div>
        </form>

    </div>
    <footer class="footer">
        <div class="container">
            <div class="footer_inner">
                <div class="header_logo">Minsk, P.Brovki,4</div>
                <div class="header_logo">+375 29-100-10-10</div>

            </div>
        </div>
        <div class="textAutor">
            <a class="nav_link" href="resume.html">Development by Bakun Pavel</a>
        </div>
    </footer>
    </div>

</section>
</body>
</html>
