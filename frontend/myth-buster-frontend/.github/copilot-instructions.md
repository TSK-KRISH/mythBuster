# MythBuster Frontend - AI Agent Instructions

## Project Overview
MythBuster is an Angular standalone components application that helps users verify myths and get AI-powered responses. The frontend interacts with a backend API running on `localhost:8080`.

## Key Architecture Patterns

### Standalone Component Architecture
- All components use Angular's standalone architecture (`standalone: true`)
- Direct imports in each component instead of module-based declarations
- Example in `src/app/components/home/home.ts`:
  ```typescript
  @Component({
    standalone: true,
    imports: [CommonModule, FormsModule, HttpClientModule],
    ...
  })
  ```

### Authentication Flow
- Components: `src/app/components/auth/{login,register}`
- Direct HTTP calls to `http://localhost:8080/api/auth/*` endpoints:
  - Login: POST to `/api/auth/login`
  - Register: POST to `/api/auth/register`

### Core Features & Data Flow
1. **Myth Submission** (`src/app/components/home/home.ts`)
   - HTTP POST to `/api/myths/ask`
   - Response handling pattern:
     ```typescript
     this.http.post<any>('http://localhost:8080/api/myths/ask', 
       { mythText: this.mythText }
     ).subscribe({
       next: (res) => { ... },
       error: (err) => { ... }
     })
     ```

2. **Myth History** (`src/app/components/all-myths/`)
   - Fetches from `/api/myths/history`
   - Displays user's previous myth submissions

## Development Workflow

### Essential Commands
1. Start development server:
   ```bash
   npm start  # Runs ng serve on http://localhost:4200
   ```
2. Run tests:
   ```bash
   npm test
   ```

### Project Conventions
1. **Component Structure**
   - Each component MUST have: `.ts`, `.html`, `.css`, `.spec.ts`
   - Standalone components with explicit imports
   - Component-scoped CSS

2. **Code Style**
   - Single quotes (enforced by Prettier)
   - Angular template parser for HTML
   - 100-character print width
   - Configuration in `package.json`:
     ```json
     "prettier": {
       "printWidth": 100,
       "singleQuote": true,
       "overrides": [{ "files": "*.html", "options": { "parser": "angular" }}]
     }
     ```

### Integration Points
1. **Backend API** (`localhost:8080`):
   - Authentication: `/api/auth/{login,register}`
   - Myth Operations: `/api/myths/{ask,history}`

2. **Server-Side Rendering**
   - Entry point: `src/server.ts`
   - Server app config: `src/app/app.config.server.ts`

## Important Note
When making HTTP calls, always use the full URL including `http://localhost:8080` as base URL management is not centralized.
