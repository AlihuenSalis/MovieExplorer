# Movie Explorer

**Propietario / Marca:** Alihuem salis / AliDev Studio

**Descripción breve:**
Movie Explorer es una aplicación Android desarrollada con **Kotlin** y **Jetpack Compose**, que consume la API pública de [TheMovieDB (TMDB)](https://www.themoviedb.org/documentation/api). Permite explorar películas y series, ver detalles (tráilers, sinopsis, elenco), marcar favoritos, buscar contenido y funcionar parcialmente offline.

Este proyecto está diseñado como **portfolio público** para demostrar competencias clave que se buscan en un **Android Senior Developer**: arquitectura limpia, modularización, KMP opcional, Compose, Coroutines/Flow, persistencia local, testing y CI/CD.

---

## 🚀 Tecnologías principales

* **Lenguaje:** Kotlin (Multiplatform opcional)
* **UI:** Jetpack Compose + Material3
* **Arquitectura:** Clean Architecture (Domain, Data, Presentation)
* **DI:** Hilt - dagger Hilt
* **Networking:** Retrofit + OkHttp
* **Persistencia:** Room, DataStore
* **Concurrencia:** Coroutines + Flow/StateFlow
* **Imágenes:** Coil
* **Testing:** JUnit, MockK, Compose UI Testing
* **Observabilidad:** Timber, Firebase Crashlytics & Analytics
* **CI/CD:** GitHub Actions (lint, build, test)

---

## 📱 Funcionalidades (MVP)

* [x] Listado de películas populares.
* [ ] Listado de películas en tendencia.
* [x] Detalle de película: poster, sinopsis, calificación, géneros, elenco, trailers.
* [x] Búsqueda de películas por título.
* [ ] Mejoras visuales en UI.
* [ ] Implementación de animaciones.
* [ ] Favoritos (persistencia local con Room). (por implementar)
* [ ] Cache básico para mostrar últimos resultados sin conexión. (por implementar)

### Futuras iteraciones

* [ ] Login + sincronización de favoritos.
* [ ] Extensión KMP para compartir lógica con iOS.
* [ ] Soporte Tablets, TV, Wear OS.

---

## 🛠️ Setup local

1. Clona el repositorio:

   ```bash
   git clone https://github.com/AlihuenSalis/movie-explorer.git
   ```
2. Abre el proyecto en Android Studio (Arctic Fox o superior).
3. Crea un archivo `local.properties` y añade tu API key de TMDB:

   ```properties
   TMDB_API_KEY=tu_api_key_aqui
   ```
4. Compila y ejecuta con:

---

## ✅ Checklist de Skills

* [x] Kotlin + Jetpack Compose
* [x] Clean Architecture (Domain/Data/Presentation)
* [x] Networking con Retrofit + manejo de errores
* [x] Persistencia local con Room + DataStore
* [x] Inyección de dependencias con Hilt
* [x] Unit tests con JUnit + MockK
* [ ] UI tests con Compose Testing
* [ ] Observabilidad (Timber + Firebase)
* [ ] CI/CD con GitHub Actions

---

## 🤝 Contribución

1. Haz un fork del repo
2. Crea una rama feature: `git checkout -b feature/nueva-funcionalidad`
3. Haz commit de tus cambios: `git commit -m 'Agrega nueva funcionalidad'`
4. Haz push a la rama: `git push origin feature/nueva-funcionalidad`
5. Crea un Pull Request

---

## 👨‍💻 Autor

**AliDev Studio** — Proyecto portfolio para prácticas avanzadas de Android Development.
