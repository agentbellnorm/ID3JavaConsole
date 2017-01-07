Meta:

Narrative:
As a user
I want to Run the algorithm on a dataset
So that i can perform a prediction.

Scenario: scenario description
Given A training dataset from $filename that creates a decision tree
When performing a prediction on a days $variables
Then the outcome should be predicted as $outcome

Examples:
filename | variables                                                                   | outcome
golf     | Day->DX;Outlook->Overcast;Temperature->Hot;Humidity->Normal;Wind->Weak      | Yes
golf     | Day->DX;Outlook->Sunny;Temperature->Hot;Humidity->Normal;Wind->Weak         | Yes
golf     | Day->DX;Outlook->Rain;Temperature->Hot;Humidity->Normal;Wind->Weak          | Yes
golf     | Day->DX;Outlook->Sunny;Temperature->Hot;Humidity->High;Wind->Weak           | No
golf     | Day->DX;Outlook->Rain;Temperature->Hot;Humidity->Normal;Wind->Strong        | No

