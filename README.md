#Project Description 
This project is an Android application that displays a list of products fetched from an API. Users can view details of a single product, add items to a cart, remove items from the cart and manage the items within the cart. The application leverages modern Android development practices, including Jetpack Compose, Hilt for dependency injection, and Retrofit for API communication.

#Features
 Product Listing Page Displays a list of products in the order they appear in the API response. Shows the product logo, name, and price. Product Detail Page Displays detailed information about a selected product, including its logo, name, price, and description. Includes "Add to Cart" and "Buy Now" buttons. Cart Management Items can be added to the cart from the product detail page. A badge is displayed showing the number of items in the cart. Items can be removed from the cart.

#Usage
 Product Listing Page: This page will be shown by default when the app is launched. Users can scroll through the list of products. Product Detail Page: When a user selects a product from the list, they will be navigated to this page where they can view more details and add the product to their cart. Cart Management: Items added to the cart will update the cart badge, and users can manage their cart by adding or removing items.

#Assumptions
 The API response provides the product details in the desired order, and no additional sorting or filtering is required on the client side. The project assumes the use of Jetpack Compose for UI development, enabling modern and declarative UI patterns. Coil are used for image loading to accommodate both traditional views and Jetpack Compose. Room is used for persisting cart items locally to handle cases where the app is used offline.

#Plugin and Library Choices
 Jetpack Compose Dependency: androidx.compose.compiler:compiler:1.5.1, androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0 Reason: Jetpack Compose is chosen for building the UI due to its modern, declarative approach to UI development. It allows for a cleaner, more readable codebase compared to XML-based layouts.

#Hilt (for Dependency Injection) Dependency: com.google.dagger:hilt-android:2.50 Reason: Hilt is selected to manage dependency injection, ensuring a scalable and testable codebase. It simplifies the setup of Dagger in Android applications.

#Retrofit & OkHttp (for Network Requests) Dependencies: com.squareup.retrofit2:retrofit:2.9.0, com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2 Reason: Retrofit, combined with OkHttp, is used for making API calls. Retrofit simplifies network requests and JSON parsing, while OkHttp provides a reliable and efficient HTTP client.

#Coil (for Image Loading) Dependencies: io.coil-kt:coil-compose:2.4.0 Reason: Glide is used for image loading in traditional views, while Coil is integrated for Jetpack Compose components. Both libraries are known for their performance and ease of use.

#Room (for Local Storage) Dependency: androidx.room:room-runtime:2.6.1 Reason: Room is used for local data storage, specifically to persist cart items, allowing users to access their cart even when offline. Room abstracts database interactions, providing a robust and SQLite-backed solution.

#Navigation Component Dependency: androidx.navigation:navigation-compose:2.7.7 Reason: Navigation Component is used to handle navigation between different screens in a type-safe manner. It ensures a consistent and predictable user experience.

#Material Components Dependency: com.google.android.material:material:1.12.0 Reason: Material Components provide a consistent design language across the app, adhering to modern UI/UX standards.

#Setup Instructions Clone the Repository Open the Project in Android Studio Ensure that you have the latest version of Android Studio installed. Sync the project with Gradle to download all dependencies. Build and Run the App Select a target device or emulator and run the project. API Integration Ensure the API is accessible and provides the required data. Modify the base URL and API configurations in the Retrofit setup if necessary. Testing UI tests are integrated using Jetpack Compose’s testing framework. Conclusion This project showcases a modern approach to Android development using Jetpack Compose, Hilt, and other essential libraries. The choices made around third-party libraries ensure a maintainable, testable, and performant application.
