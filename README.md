# Assignment-submission

## Requirements

A student should be able to login to our application and see a dashboard that outlines the following info:

- What assignment are due, and when.
- (perhaps) a page to review past assignment submission.
- Status of an assignment that’s currently under review and if it needs tweaking.
- A way to submit (or re-submit) an assignment for review.

When submitting an assignment, the student will be asked to provide the following info:

- Github URL to public Repo
- Which branch is the correct one

Once submitted, the status updates from “pending submission” → “submitted”

Bonus feature: Leverage GitHub API to verify if a URL points to a public repo, and get a list of branches to populate the “branch” dropdown.

Once a student has submitted an assignment, an email notification should be sent to all active code reviewers letting them know that a new code review is ready. The first code reviewer to claim the review will be able to start working on it (and now it’s unavailable to others for review).

Once a code reviewer has claimed a submitted assignment, the status changes from “submitted” → “in review”

Bonus feature: If a code reviewer has claimed a review and hasn’t completed it within 24 hours it is sent back to the previous status, and anyone can claim it.

When a code reviewer rejects an assignment, the status will change from “submitted” → “needs update”. When this happens, a notification will be sent to the student telling them  they need to do more work on that assignment. Once they’ve done their  work, they can revisit that assignment for re-submission (perhaps prompt them to make sure they’ve pushed their changes).

When a student re-submits an assignment, they will be given a chance to change their github url + branch, then it will move from “needs updating” → “in review” and get assigned back to the same code reviewer. A notification will be sent to the code reviewer.

When a code reviewer has successfully completed their review, they will be able to submit their finished review (by adding a URL to a video recording) and then a notification will be sent back to the student. The status will change from “in review” → “completed”

Also, for each cohort of students, we will need to have an API that allows for an Admin role to create user accounts

## Daily batch jobs

Notifies code reviewers for reviews that are sitting stagnant and waiting for review.

Notifies students if their rejected assignments are still needing tweaking

## Appendix

Statuses: pending submission, submitted, in review, needs update, completed

## Entities and relationships
User (Class → Database Table) 

- role (student role, code reviewer role, admin role)
- cohortStartDate: LocalDate
- username: String
- password: String
- assignments: List<Assignment>: Assignment

Assignment (Class → Database Table)

- status: String (pending submission, submitted, in review, needs update, completed)
- githubURL: String
- Branch: String
- codeReviewVideoUrl: String
- assignedTo: User

EmailNotificationService (Class)

- messageBody
- subjectLine
- toAddress
- fromAddress

UserService (Class)

- createAccount ( )