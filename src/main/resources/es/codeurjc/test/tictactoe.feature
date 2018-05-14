Feature: TicTacToe
  As a user I want to put some moves in the board and check the result.

Scenario: Play a game and check the result.
    Given I have a tictactoe game at http://localhost:8080
    And player_one is playerOne
    And player_two is playerTwo
    When this moves are played [0,3,1,4,2]
    Then the result is playerOne wins!

Scenario: Play a game and check the result.
    Given I have a tictactoe game at http://localhost:8080
    And player_one is playerOne
    And player_two is playerTwo
    When this moves are played [0,3,1,4,8,5]
    Then the result is playerTwo wins!

Scenario: Play a game and check the result.
    Given I have a tictactoe game at http://localhost:8080
    And player_one is playerOne
    And player_two is playerTwo
    When this moves are played [4,0,7,1,2,6,3,5,8]
    Then the result is draw!

Scenario Outline: Play a game and check the result.
    Given I have a tictactoe game at http://localhost:8080
    And player_one is playerOne
    And player_two is playerTwo
    When this moves are played <moves>
    Then the result is <result>!

    Examples:
    |       moves         |     result     |
    | [0,3,1,4,2]         | playerOne wins |
    | [0,3,1,4,8,5]       | playerTwo wins |
    | [4,0,7,1,2,6,3,5,8] | draw           |