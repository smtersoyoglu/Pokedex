#  <img src="https://github.com/user-attachments/assets/12aa93a6-6c60-42ca-b6c0-f9530864fa3c" width="50"/> Pokedex App

A modern Android Pokedex application built with **Jetpack Compose**, following **Clean Architecture** and **MVVM** patterns. The app fetches data from the public **PokeAPI**, showcasing a paginated list of Pokémons with detailed stats and types.

## 🚀 Features

- 🧩 **Modern UI** with Jetpack Compose
- ⚙️ **Clean Architecture** with well-separated layers (data, domain, presentation)
- 🔄 **Paging 3** support for efficient Pokémon list loading
- 🔍 **Detail screen** for each Pokémon including types, stats, height, and weight
- 🎨 **Dynamic UI color** based on Pokémon image dominant color
- 🧭 **Jetpack Navigation Compose** for seamless screen transitions
- 💉 **Hilt** for dependency injection
- 📦 **Retrofit & Kotlin Serialization** for network layer
- 📷 **Coil** for image loading
- 🎞️ **Lottie** for loading animations

---

## 📸 Screenshots

| Splash Screen | Home (Paging) | Detail Screen |
|:-------------:|:-------------:|:-------------:|
| ![Splash](https://github.com/user-attachments/assets/8cde542f-ad55-4874-a35e-14a14104de23) | ![Home](https://github.com/user-attachments/assets/b7784a2e-a7be-442c-8ed5-1072769a06bc) | ![Detail](https://github.com/user-attachments/assets/934d7efa-619c-4fac-a31a-68a03ee74b0c) |


---

## 🧱 Architecture

This project strictly follows **Clean Architecture** layered design:

### 🗂 Data Layer (`data`)
Responsible for all external data operations and their transformations:
- `remote`: Retrofit API interface (`PokeApi`)
- `repository`: Concrete implementation of repository interfaces
- `paging`: Custom `PagingSource` for lazy loading
- `mappers`: Converts DTOs to domain models

### 🧠 Domain Layer (`domain`)
Contains business logic and application-specific rules:
- `model`: Core models used throughout the app
- `repository`: Interfaces for data sources
- `usecase`: Encapsulates application logic (`GetPokemonListUseCase`, `GetPokemonDetailUseCase`)

### 🎨 Presentation Layer (`presentation`)
The UI layer built with **Jetpack Compose**:
- `pokemon_list`: Pokémon listing screen and its ViewModel
- `pokemon_detail`: Detail screen showing full Pokémon info
- `splash_screen`: Splash screen with Lottie animation (optional)

### 🧰 Common Layer (`common`)
Shared components and utility functions used across the app:
- `Constants`, `Resource`, `safeCall`, and image color extraction

### ⚙️ Dependency Injection (`di`)
- `NetworkModule` and `RepositoryModule` set up using **Hilt**

### 🧭 Navigation (`navigation`)
- Centralized screen routing with `PokemonNavGraph.kt` and route management


---

## 🛠️ Tech Stack

| Tech | Description |
|------|-------------|
| **Jetpack Compose** | Modern declarative UI toolkit |
| **Hilt** | Dependency injection |
| **Retrofit + Kotlin Serialization** | API communication |
| **Paging 3** | Efficient data pagination |
| **Coil** | Image loading |
| **Navigation Compose** | Screen navigation |
| **Lottie** | Animations |
| **Palette API** | Extracts dominant color for themed detail screen |

---
## 📁 Package Structure

```text
com.smtersoyoglu.pokedex
│
├── common                  # Utility functions, sealed classes, shared logic
│   ├── Constants.kt
│   ├── Resource.kt
│   ├── safeCall.kt
│   └── extractDominantColor.kt
│
├── data
│   ├── remote              # Retrofit API interface and DTOs
│   ├── paging              # PagingSource implementation
│   ├── mappers             # DTO → Domain model mapping functions
│   └── repository          # Repository implementation
│
├── di                     # Hilt modules
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
│
├── domain
│   ├── model              # Core domain models
│   ├── repository         # Abstract repository interfaces
│   └── usecase            # Use cases encapsulating app logic
│
├── navigation
│   ├── PokemonNavGraph.kt
│   └── Screens.kt         # Strongly-typed route definitions
│
├── presentation
│   ├── pokemon_list       # List screen UI + ViewModel + State
│   ├── pokemon_detail     # Detail screen UI + ViewModel + State
│   └── splash_screen      # Optional splash screen
│
├── ui.theme               # Compose theming
│
├── MainActivity.kt        # Root composable holder
└── PokedexApplication.kt  # Application class (Hilt setup)
