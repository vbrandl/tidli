---
title: "Tidli - Take it, don't leave it"
author: [Brandl Valentin]
date: \today
subject: "Tidli Use Cases"
tags: [SW, JavaEE, OTHR, Use Cases]
lang: en
titlepage: true
lof: true
...

# Tidli - Take it, don't leave it

## Use Cases

![Usecases](./usecases.png)

### Administrator

#### Activate Shop

 * Log in
 * Filter shop list for deactivated shops
 * Activate selected shop

#### Delete Account or Shop

 * Log in
 * Search through account list
 * Delete selected account

### Shop

#### Register Shop

 * Fill register form
 * Finish registration
 * Wait for account activation by an administrator

#### Create Opening Times

 * Log in
 * Create new opening day or day range
 * Enter opening times for day or range
 * Save

#### Create Article

 * Log in
 * Fill form and upload image
 * Finish creation

#### Create Offer

 * Log in
 * Select existing product or create a new one
 * Enter price and amount
 * Finish creation

#### Decrement Available Products for Offer

 * Log in
 * Select offer
 * Decrement counter by amount of sold products

### Administrator or Shop

#### Delete Offer

 * Log in
 * Search offer in offer list
 * Delete selected offer

### User

#### Register User

 * Fill registration form
 * Finish registration


#### Search offer

 * Optionally log in
 * Enter search parameters or use the address that is connected to the
    logged in account
 * View offers

#### Rate Shop

 * Log in
 * Search for shop to rate
 * Enter rating

#### Rate Article

 * Log in
 * Search for offer containing the product
 * Enter rating

### Shop or User

#### Open Support Ticket

 * Log in
 * Fill ticket form
 * Submit ticket
 * Ticket will be created in the TicketSystem (Georg)
