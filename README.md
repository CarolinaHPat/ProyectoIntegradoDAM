# Dashboard de Tickets - Proyecto Integrado DAM

Sistema de gestión de tickets con arquitectura fullstack: backend en Spring Boot (Java) y frontend en Angular.

## Requisitos previos

- **Node.js** (v18+) y npm
- **Java** (JDK 11+)
- **Maven** (para el backend)
- **IntelliJ IDEA** (recomendado para el backend)

## Estructura del proyecto

```
Proyecto Integrado/
├── frontend/              # Aplicación Angular
│   ├── src/
│   ├── package.json
│   └── angular.json
├── src/                   # Backend Spring Boot
│   ├── main/java/
│   └── main/resources/
├── pom.xml               # Configuración Maven
└── README.md
```

## Instalación y ejecución

### 1. Backend (Spring Boot)

**Opción A: Desde IntelliJ IDEA**
1. Abre el proyecto en IntelliJ
2. Espera a que se indexe y descarguen las dependencias Maven
3. Ejecuta `DashboardApplication.java` (botón Run o Shift+F10)
4. El backend estará disponible en `http://localhost:8080`

**Opción B: Desde terminal**
```bash
mvn spring-boot:run
```

### 2. Frontend (Angular)

**Primera vez (instalar dependencias):**
```bash
cd frontend
npm install
```

**Desarrollo (con hot reload):**
```bash
cd frontend
npm start
```
- La aplicación estará disponible en `http://localhost:4200`
- Los cambios se reflejan automáticamente

**Producción (compilar):**
```bash
cd frontend
npm run build
```
- Los archivos compilados se generan en `frontend/dist/`

## Flujo de desarrollo recomendado

1. **Terminal 1 - Backend:**
   ```bash
   # Desde la raíz del proyecto
   mvn spring-boot:run
   ```

2. **Terminal 2 - Frontend:**
   ```bash
   cd frontend
   npm start
   ```

3. Abre el navegador en `http://localhost:4200`

## Tecnologías principales

### Backend
- **Spring Boot** - Framework web
- **Spring Data JPA** - Persistencia de datos
- **MySQL/H2** - Base de datos

### Frontend
- **Angular 20** - Framework web
- **TypeScript** - Lenguaje de programación
- **RxJS** - Programación reactiva (manejo de observables y flujos asincronos)
- **Angular Router** - Enrutamiento
- **Angular Forms** - Gestión de formularios

## Características principales

- ✅ Gestión de tickets (crear, editar, eliminar, listar)
- ✅ Asignación de técnicos
- ✅ Filtrado por estado, prioridad y técnico
- ✅ Interfaz reactiva con Angular

## Scripts disponibles

### Frontend
```bash
npm start      # Inicia servidor de desarrollo
npm run build  # Compila para producción
npm test       # Ejecuta pruebas unitarias
npm run watch  # Compila en modo watch
```

### Backend
```bash
mvn clean install        # Limpia y compila
mvn spring-boot:run      # Inicia la aplicación
mvn test                 # Ejecuta pruebas
```

## Configuración

### Backend
- Puerto por defecto: `8080`
- Archivo de configuración: `src/main/resources/application.properties`

### Frontend
- Puerto por defecto: `4200`
- Configuración: `angular.json`

## Troubleshooting

**El frontend no se conecta al backend:**
- Verifica que el backend esté corriendo en `http://localhost:8080`
- Revisa la consola del navegador (F12) para errores CORS

**Error de dependencias en npm:**
```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
```

**Puerto 4200 ya está en uso:**
```bash
ng serve --port 4300
```

