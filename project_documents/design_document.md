# Team_Bark_Park Design Document

## Instructions

*Save a copy of this template for your team in the same folder that contains
this template.*

*Replace italicized text (including this text!) with details of the design you
are proposing for your team project. (Your replacement text shouldn't be in
italics)*

*You should take a look at the example design document in the same folder as
this template for more guidance on the types of information to capture, and the
level of detail to aim for.*

## *Project Title* Design

## 1. Problem Statement

When a dog owner wants to find or learn more about a dog park, their best option right now is to do a basic web search for nearby dog parks
on the search engine of their choice. The results of this search are often limited, cluttered with non-relevant findings and 
lacking in valuable detail and information regarding the parks that are found.

This design document details Bark Park, a new service that will provide users access to a geographically organized database
of dog parks along with specific information and reviews for each park. It is designed to interact with a
client application which will allow customers to view all dog parks or search for dog parks based on specific criteria, write, edit, delete, and view reviews of dog parks


## 2. Top Questions to Resolve in Review

1. What are the primary data types and associated attributes?
   1. Should we decouple related dependent objects?
      1. For example, should a dogpark object maintain a list of its reviews or should this be retrieved
         on demand by a request to the reviews table?
2. Should we include the ability for users to upload dogpark photos in the initial scope?
3. How do we want to manage permissions for adding, removing, and updating parks?
   1. Our initial discussions have led us to consider allowing users to submit requests that would be handled by an admin.
   This may require an additional requests table in the database, additional admin functionality, and increased
   complexity in general.
4. How do we want to store location data?
   1. Possibilities include geographical coordinates, city names, etc.

## 3. Use Cases

U1. *As a Bark Park customer, I want to view a list of all parks*

U2. *As a Bark Park customer, I want to view a list of all nearby parks when I search by location*
    
U3. *As a Bark Park customer, I want to view a filtered list of parks when I search by tags*

U4. *As a Bark Park customer, I want to view a sorted and filtered list of parks when I search by reviews*

U5. *As a Bark Park customer, I want to view details and reviews for a park*

U6. *As a new Bark Park customer, I want to create a user account*

U7. *As a Bark Park customer, I want to edit my user account*

U8. *As a Bark Park customer, I want to delete my user account*

U9. *As a Bark Park customer, I want to log in/out of my user account*

U10. *As a Bark Park customer, I want to submit a park creation request*

U11. *As a Bark Park customer, I want to submit a park deletion request*

U12. *As a Bark Park customer, I want to submit a park update request*

U13. *As a Bark Park customer, I want to create a review of a park*

U14. *As a Bark Park customer, I want to edit my review of a park*

U15. *As a Bark Park customer, I want to delete my review of a park*


## 4. Project Scope

*Clarify which parts of the problem you intend to solve. It helps reviewers know
what questions to ask to make sure you are solving for what you say and stops
discussions from getting sidetracked by aspects you do not intend to handle in
your design.*

### 4.1. In Scope

1. Retrieve a list of all nearby parks
2. Retrieve a list of parks sorted and/or filtered by preference
3. Create, delete, and edit a review for a specific park
4. Submit a request to create, remove, or edit a specific park


### 4.2. Out of Scope

1. User ability to *favorite* a park and persist this information in the database
2. User ability to upload photos
3. Integration with a mapping api (like Google maps) to generate driving directions
4. Donation option to support local government maintenance of the park
5. Ranking system for *most popular* dogs in each park
6. Allow customer to submit requests to add, remove, or update existing parks

# 5. Proposed Architecture Overview

*Describe broadly how you are proposing to solve for the requirements you
described in Section 2.*

*This may include class diagram(s) showing what components you are planning to
build.*

*You should argue why this architecture (organization of components) is
reasonable. That is, why it represents a good data flow and a good separation of
concerns. Where applicable, argue why this architecture satisfies the stated
requirements.*

# 6. API

## 6.1. Public Models

*Define the data models your service will expose in its responses via your
*`-Model`* package. These will be equivalent to the *`PlaylistModel`* and
*`SongModel`* from the Unit 3 project.*

## 6.2. *First Endpoint*

*Describe the behavior of the first endpoint you will build into your service
API. This should include what data it requires, what data it returns, and how it
will handle any known failure cases. You should also include a sequence diagram
showing how a user interaction goes from user to website to service to database,
and back. This first endpoint can serve as a template for subsequent endpoints.
(If there is a significant difference on a subsequent endpoint, review that with
your team before building it!)*

*(You should have a separate section for each of the endpoints you are expecting
to build...)*

## 6.3 *Second Endpoint*

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

# 7. Tables

*Define the DynamoDB tables you will need for the data your service will use. It
may be helpful to first think of what objects your service will need, then
translate that to a table structure, like with the *`Playlist` POJO* versus the
`playlists` table in the Unit 3 project.*

# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*
