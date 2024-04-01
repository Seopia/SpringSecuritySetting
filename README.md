대학교 프로젝트 진행을 위한 Spring Security 적용 입니다.

- 회원가입 로직
- 로그인 로직

/login 엔드포인트에 body에
username : root
password : root
처럼 요청하게 되면

SecurityConfig.class 에 직접 추가한 필터가 작동하여 요청을 중간에 필터링 하게 된다. (자바 Proxy 개념)
추가한 필터는 직접 만든 LoginFilter.class 이며 이 클래스의 생성자는 인자로 AuthenticationManager를 받는다.

어찌됐든 가로채면 attempAuthentication 메서드가 작동하는데
body 에 있는 username, password를 빼서 UsernamePasswordAuthenticationToken에 담고, 매니저에게 보낸다.

매니저는 UserDetailsService를 구현한 CustomUserDetailsService.class를 사용해 DB에서 id, pw를 찾아 검증하게 되고 DB에 값이 존재한다면
CustomUserDetails에 담아 정보를 return하고 
LoginFilter.class의 successfulAuthentication() 메서드를 호출한다.

JWT 토큰은 Header, Payload, Signature 로 이루어져 있다.
Header : JWT임을 명시한다, 사용한 암호화 알고리즘을 명시한다. 

Payload : 정보를 담는다.

Signature : 암호화 알고리즘이 담겨있다 (BASE64(Header) + BASE64(Payload) + 암호화 키)

외부에서 열람해도 되는 정보만 담아야한다. 비밀번호 등 민감한 것들 금지!
만약 헤더나, 페이로드를 조작하여 보내더라도 시그니쳐와 다르기 때문에 토큰이 반려되게 된다.
