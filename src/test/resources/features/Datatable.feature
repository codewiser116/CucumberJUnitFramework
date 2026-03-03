Feature: to practice datatables

  Scenario Outline: convert datatable to list
    Given the following clinic departments exist
      | Cardiology |
      | Neurology  |
      | Pediatrics |
      | Oncology   |
    When patient selects "desease" in each department
      | Cardiology |
      | Neurology  |
    And patient selects 3 appointments for doctor "<doctor>" at hospital "<hospital>"
    And patient selects guests list
      | Sarah | friend  |
      | John  | father  |
      | Yzat  | husband |

    Examples:
      | doctor | hospital   |
      | Chen   | Aurora     |
      | Connor | Naperville |
      | Anna   | Texas      |