<p align="center">
      <img src="https://i.ibb.co/z6CXpm6/75775.png" width="256" height="256">
</p>

<p align="center">
   <img src="https://img.shields.io/badge/Spring--Boot-2.6.4-green" alt="Spring-Boot 3.0.0">
   <img src="https://img.shields.io/badge/REST-API-yellow" alt="REST API">
</p>

## Информация

<p>Калькулятор расчёта отпускных. Написан для тестового задания для поступления в учебный центр компании Neoflex.</p>
<p>Содержит один энд поинт /calculate с двумя обязательными параметрами (averageSalaryPerYear, vacationDaysAmount)
и один необязательным (vacationStartDate)</p>

## Пример запроса
<p>Запрос для получения отпускных (базовое задание): 
<br/>/calculate?averageSalaryPerYear=1000000&vacationDaysAmount=14 <br/>
Ожидаемый результат: {"vacationPayment":39817.96}</p>

<p>Запрос для получения отпускных с указанием даты выхода в отпуск (дополнительное задание): <br/>
/calculate?averageSalaryPerYear=1000000&vacationDaysAmount=14&vacationStartDate=2024-04-01 <br/>
Ожидаемый результат: {"vacationPayment":28441.4}</p>

## Стек
- Spring Boot
- Spring MVC
- Spring Validation
- Lombok
- Spring Test

