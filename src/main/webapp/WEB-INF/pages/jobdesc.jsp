<%--
  Created by IntelliJ IDEA.
  User: sherxon
  Date: 2/22/16
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href=<%request.getContextPath();%>"/assets/css/bootstrap.css"/>
</head>
<body>
<div class="" style="margin: 2%">
<div id="companyName" class="panel-title">CompanyName</div>
 <div id="position">Position</div>
    <div id="salary">1,500,000</div>
    <div id="created">1,500,000</div>
    <div id="desc">
        <div class="VacancyView_details">
            <div class="VacancyView_salary">
                <span class="h_color_green"><b>по договоренности</b></span>, <b class="h_color_gray_dk">опыт работы от 1 до 3 лет</b>,
                <b class="h_color_gray_dk">высшее образование</b>
            </div>
            <button value="like" onclick="ok.performClick(this.value)">Like</button>
            <div class="VacancyDetails_section">
                <div class=""><b>Должностные обязанности:</b></div>
                <div class="VacancyDetails_item">1. Разрабатывает контент (содержание) рекламы, рекламные тексты, пишет рекламные и PR-статьи.</div>
                <div class="VacancyDetails_item">2. Разрабатывает положительный имидж компании.</div>
                <div class="VacancyDetails_item">3. Обеспечивает информационное наполнение рекламы.</div>
                <div class="VacancyDetails_item">4. Организует презентацию слоганов, наименований, сценариев и статей руководству. </div>
                <div class="VacancyDetails_item">5. Подготавливает новостные и пресс-релизы.</div>
                <div class="VacancyDetails_item">6. Создает «информационные поводы».</div>
                <div class="VacancyDetails_item">7. Осуществляет информационно-аналитическую и редакторскую работу.</div>
                <div class="VacancyDetails_item">8. Участвует в издании информационного бюллетеня.</div>
            </div>

            <div class="VacancyDetails_section">
                <div class=""><b>Требования:</b></div>

                <div class="VacancyDetails_item">* Желательно знание административной панели Magento, или аналогичных CMS</div>
                <div class="VacancyDetails_item">* Внимательность</div>
                <div class="VacancyDetails_item">* Усидчивость</div>
                <div class="VacancyDetails_item">* Грамотность</div>
                <div class="VacancyDetails_item">* Высокая эффективность работы</div>
                <div class="VacancyDetails_item">* Русский&nbsp;язык свободное владение</div>
            </div>

            <div class="VacancyDetails_section">
                <div class="VacancyDetails_title">Мы предлагаем:</div>
                <div class="VacancyDetails_item">Пятидневная рабочая неделя, с 9:00 до 18:00.</div>
                <div class="VacancyDetails_item">Оклад + премия</div>
                <div class="VacancyDetails_item">Полный рабочий день</div>
                <div class="VacancyDetails_item">Трудоустройство согласно Трудовому Кодексу</div>
                <div class="VacancyDetails_item">На территории работодателя в центре города</div>
                <div class="VacancyDetails_item h_first_letter_uppercase">
                    Полный рабочий день
                </div>
                <div class="VacancyDetails_item h_first_letter_uppercase">
                    на территории работодателя
                </div>
            </div>


        </div>
    </div>

</div>

</body>
<script src=<%request.getContextPath();%>"/assets/js/jquery.js" type="text/javascript"></script>
<script src=<%request.getContextPath();%>"/assets/js/bootstrap.min.js" type="text/javascript"></script>

<script type="text/javascript">
  var vacancy=JSON.parse('${item}');
  $(document).ready(function () {
      $('#companyName').html(vacancy.company_name);
      $('#position').html(vacancy.vac_position);
      $('#salary').html(vacancy.st_price);
      $('#created').html(vacancy.st_date);

  })

</script>

</html>
