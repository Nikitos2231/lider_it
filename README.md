# Проект: "REST-сервис для хранения данных о спортивных командах"

---

## Инструкция по запуску
    1. Для запуска приложения нужно создать базу данных и подправить файл src/main/resources/hibernate.properties.

---

## Описание методов API

<table>
    <thead>
        <tr>
            <th>CRUD</th>
            <th>HTTP</th>
            <th>API</th>
            <th>Request Body</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=4>READ</td>
            <td rowspan=4>GET</td>
            <td rowspan=4>/teams</td>
            <td rowspan=4>
            { <br>
                &emsp;"page": "1", <br>
                &emsp;"maxCount": "100", <br>
                &emsp;"sportType": "0", <br>
                &emsp;"startDate": "1950-12-12", <br>
                &emsp;"endDate": "1997-12-12" <br>
            }
            </td>
            <td rowspan=4>
            Получить информацию о командах <br><br>
            page - страница <br>
            maxCount - количество записаей <br>
            sportType - задается в числах <br>
            startDate - нижняя граница диапазона <br>
            endDate - верхняя граница диапазона <br>
    <br>        
            Все параметры необязательны <br>
            и могут комбинироваться.
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>CREATE</td>
            <td rowspan=4>POST</td>
            <td rowspan=4>/teams</td>
            <td rowspan=4>
            { <br>
                &emsp;"teamName": "Zenit", <br>
                &emsp;"sportType": "BASKETBALL", <br>
                &emsp;"dateOfCreate": "1995-06-03" <br>
            }
            </td>
            <td rowspan=4>
            Создать команду. <br> <br>
            Все параметры обязательны.
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>READ</td>
            <td rowspan=4>GET</td>
            <td rowspan=4>/teams/{team_id}/players</td>
            <td rowspan=4>
            { <br>
                &emsp;"page": "1", <br>
                &emsp;"maxCount": "100", <br>
                &emsp;"roleOrPosition": "forward", <br>
            }
            </td>
            <td rowspan=4>
            Получить информацию о игроках <br>команды
             <br><br>
            page - страница <br>
            maxCount - количество записаей <br>
            roleOrPosition - позиция в команде
            <br><br>
                        Все параметры необязательны <br>
            и могут комбинироваться.
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>DELETE</td>
            <td rowspan=4>DEL</td>
            <td rowspan=4>/teams/{team_id}</td>
            <td rowspan=4>
            </td>
            <td rowspan=4>
            Удалить команду
             <br><br>
             Игроки команды тоже будут удалены
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>CREATE</td>
            <td rowspan=4>POST</td>
            <td rowspan=4>/teams/{team_id}/players</td>
            <td rowspan=4>
                { <br>
                &emsp;"name": "Igor",<br>
                &emsp;"surname": "Volhin",<br>
                &emsp;"patronymic": "Valerievich",<br>
                &emsp;"dateOfBirth": "1997-10-30",<br>
                &emsp;"roleOrPosition": "forward"<br>
                }
            </td>
            <td rowspan=4>
            Добавить игрока в команду
            <br><br>
            Все параметры обязательны.
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>READ</td>
            <td rowspan=4>GET</td>
            <td rowspan=4>/teams/{team_id}/players/{player_id}</td>
            <td rowspan=4>
            </td>
            <td rowspan=4>
            Получить игрока в команде
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>DELETE</td>
            <td rowspan=4>DEL</td>
            <td rowspan=4>/teams/{team_id}/players/{player_id}</td>
            <td rowspan=4>
            </td>
            <td rowspan=4>
            Удалить игрока в команде
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>UPDATE</td>
            <td rowspan=4>PATCH</td>
            <td rowspan=4>/teams/{team_id}</td>
            <td rowspan=4>
            { <br>
                &emsp;"teamName": "Zenit", <br>
                &emsp;"sportType": "BASKETBALL", <br>
                &emsp;"dateOfCreate": "1995-06-03" <br>
            }
            </td>
            <td rowspan=4>
            Обновить команду. <br> <br>
            Все параметры обязательны.
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>UPDATE</td>
            <td rowspan=4>PATCH</td>
            <td rowspan=4>/teams/{team_id}/players/{player_id}</td>
 <td rowspan=4>
                { <br>
                &emsp;"name": "Igor",<br>
                &emsp;"surname": "Volhin",<br>
                &emsp;"patronymic": "Valerievich",<br>
                &emsp;"dateOfBirth": "1997-10-30",<br>
                &emsp;"roleOrPosition": "forward"<br>
                }
            </td>
            <td rowspan=4>
            Обновить игрока в команде
            <br><br>
            Все параметры обязательны.
            </td>
        </tr>
    </tbody>
        <tbody>
        <tr>
            <td rowspan=4>UPDATE</td>
            <td rowspan=4>PATCH</td>
            <td rowspan=4>/teams/{team_id}/players/{player_id}/transfer-to/{new_team_id}</td>
 <td rowspan=4>
            </td>
            <td rowspan=4>
            Перевести игрока из одной
            команды в другую
            </td>
        </tr>
    </tbody>
    <tbody>
        <tr>
            <td rowspan=4>READ</td>
            <td rowspan=4>GET</td>
            <td rowspan=4>/players</td>
 <td rowspan=4>
            </td>
            <td rowspan=4>
            Получить всех игроков
            </td>
        </tr>
    </tbody>
</table>